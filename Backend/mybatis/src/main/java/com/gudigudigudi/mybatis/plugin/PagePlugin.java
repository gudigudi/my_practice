package com.gudigudigudi.mybatis.plugin;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.expression.spel.support.ReflectionHelper;

import javax.xml.bind.PropertyException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PagePlugin implements Interceptor {

    private static String dialect = "";
    private static String pageSqlId = "";

    @SuppressWarnings("unchecked")
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectorHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectorHelper.getValueByFieldName(delegate, "mappedStatement");

            if (mappedStatement.getId().matches(pageSqlId)) {
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject error");
                } else {
                    Connection connection = (Connection) invocation.getArgs()[0];
                    String sql = boundSql.getSql();
                    String countSql = "select count(0) from (" + sql + ") myCount";
                    System.out.println("total sql statement:" + countSql);
                    PreparedStatement countStmt = connection.prepareStatement(countSql);
                    BoundSql countBS = new BoundSql(
                            mappedStatement.getConfiguration(), countSql,
                            boundSql.getParameterMappings(), parameterObject);
                    setParameters(countStmt, mappedStatement, countBS, parameterObject);
                    ResultSet rs = countStmt.executeQuery();
                    int count = 0;
                    if (rs.next()) {
                        count = rs.getInt(1);
                    }
                    rs.close();
                    countStmt.close();

                    PageInfo pageInfo = null;
                    if (parameterObject instanceof PageInfo) {
                        pageInfo = (PageInfo) parameterObject;
                        pageInfo.setTotalResult(count);
                    } else if (parameterObject instanceof Map) {
                        Map<String, Object> map = (Map<String, Object>) parameterObject;
                        pageInfo = (PageInfo) map.get("page");
                        if (pageInfo == null) {
                            pageInfo = new PageInfo();
                        }
                        pageInfo.setTotalResult(count);
                    } else {
                        Field pageField = ReflectorHelper.getFieldByFieldName(parameterObject, "page");
                        if (pageField != null) {
                            pageInfo = (PageInfo) ReflectorHelper.getValueByFieldName(parameterObject, "page");
                            if (pageInfo == null) {
                                pageInfo = new PageInfo();
                            }
                            pageInfo.setTotalResult(count);
                            ReflectorHelper.setValueByFieldName(parameterObject, "page", pageInfo);
                        } else {
                            throw new NoSuchFieldException(parameterObject.getClass().getName());
                        }
                    }
                    String pageSql = generatePageSql(sql, pageInfo);
                    System.out.println("page sql:" + pageSql);
                    ReflectorHelper.setValueByFieldName(boundSql, "sql", pageSql);
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        dialect = properties.getProperty("dialect");
        if (dialect == null || dialect.equals("")) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
        pageSqlId = properties.getProperty("pageSqlId");
        if (dialect == null || dialect.equals("")) {
            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
    }

    private void setParameters(PreparedStatement preparedStatement, MappedStatement mappedStatement, BoundSql boundSql,
                               Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters")
                .object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(preparedStatement, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    private String generatePageSql(String sql,PageInfo pageInfo){
        if (pageInfo!=null&&(dialect!=null||!dialect.equals(""))){
            StringBuffer pageSql=new StringBuffer();
            if ("mysql".equals(dialect)){
                pageSql.append(sql);
                pageSql.append(" limit "+ pageInfo.getCurrentResult()+","+pageInfo.getShowCount());
            }else if ("oracle".equals(dialect)){
                pageSql.append("select * from (select tmp_tb.*, ROWNUM row_id from (");
                pageSql.append(sql);
                pageSql.append(") tmp_tb where ROWNUM<=");
                pageSql.append(pageInfo.getCurrentResult()+pageInfo.getShowCount());
                pageSql.append(") where row_id>");
                pageSql.append(pageInfo.getCurrentResult());
            }
            return pageSql.toString();
        }else {
            return sql;
        }
    }
}

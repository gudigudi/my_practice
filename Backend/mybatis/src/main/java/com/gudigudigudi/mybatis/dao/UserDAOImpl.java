package com.gudigudigudi.mybatis.dao;

import com.gudigudigudi.mybatis.model.Article;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl extends SqlSessionDaoSupport implements UserDAO{

    @Override
    public List<Article> getUserArticles(int userid) {
        return this.getSqlSession()
                .selectList("com.gudigudigudi.mybatis.inter.IUserOperation.getUserArticles",userid);
    }
}

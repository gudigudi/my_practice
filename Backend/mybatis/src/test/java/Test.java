import com.gudigudigudi.mybatis.inter.IUserOperation;
import com.gudigudigudi.mybatis.model.Article;
import com.gudigudigudi.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

public class Test {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("config/mybatis/Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
//        SqlSession session = sqlSessionFactory.openSession();
//        try {
//            IUserOperation userOperation = session.getMapper(IUserOperation.class);
////            User user = (User) session.selectOne("com.gudigudigudi.mybatis.inter.model.User.selectUserByID", 1);
//            User user = userOperation.selectUserByID(1);
//            System.out.println(user.getUserAddress());
//            System.out.println(user.getUserName());
//        } finally {
//            session.close();
//        }

//        addUser();
//        updateUser();
//        getUserList("%");
//        deleteUser(2);
//        getUserList("%");
        getUserArticles(1);
    }

    public static void getUserList(String userName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation = session.getMapper(IUserOperation.class);
            List<User> users = userOperation.selectUsers(userName);
            for (User user : users) {
                System.out.println(user.getId() + ":" + user.getUserName() + ":" + user.getUserAddress());
            }
        } finally {
            session.close();
        }
    }

    public static void addUser() {
        SqlSession session = sqlSessionFactory.openSession();
        User user = new User();
        user.setUserAddress("人民广场");
        user.setUserName("飞鸟");
        user.setUserAge(80);

        try {
            IUserOperation userOperation = session.getMapper(IUserOperation.class);
            userOperation.addUser(user);
            session.commit();
            System.out.println("当前增加的用户id为:" + user.getId());
        } finally {
            session.close();
        }
    }

    public static void updateUser() {
        SqlSession session = sqlSessionFactory.openSession();

        try {
            IUserOperation userOperation = session.getMapper(IUserOperation.class);
            User user = userOperation.selectUserByID(4);
            user.setUserAddress("浦东创新园区");
            userOperation.updateUser(user);
            session.commit();
        } finally {
            session.close();
        }
    }

    public static void deleteUser(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation = session.getMapper(IUserOperation.class);
            userOperation.deleteUser(id);
            session.commit();
        } finally {
            session.close();
        }
    }

    public static void getUserArticles(int userid){
        SqlSession session =sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation =session.getMapper(IUserOperation.class);
            List<Article> articles = userOperation.getUserArticles(userid);
            for (Article article:articles){
                System.out.println(article.getTitle()+":"+article.getContent()+
                ":author is:"+article.getUser().getUserName()+":address is:"+
                article.getUser().getUserAddress());
            }
        }finally {
            session.close();
        }
    }
}

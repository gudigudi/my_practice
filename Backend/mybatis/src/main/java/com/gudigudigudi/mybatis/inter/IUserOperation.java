package com.gudigudigudi.mybatis.inter;

import com.gudigudigudi.mybatis.model.Article;
import com.gudigudigudi.mybatis.model.User;
import com.gudigudigudi.mybatis.plugin.PageInfo;

import java.util.List;

public interface IUserOperation {

    public User selectUserByID(int id);

    public List<User> selectUsers(String userName);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

    public List<Article> getUserArticles(int id);

    public List<Article> selectArticleListPage(PageInfo pageInfo,int currentPage);
}

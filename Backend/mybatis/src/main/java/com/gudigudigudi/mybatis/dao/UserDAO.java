package com.gudigudigudi.mybatis.dao;

import com.gudigudigudi.mybatis.model.Article;

import java.util.List;

public interface UserDAO {

    public List<Article> getUserArticles(int userid);

}

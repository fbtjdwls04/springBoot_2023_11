package com.koreaIT.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreaIT.demo.vo.Article;

@Mapper
public interface ArticleDao {

	public Article writeArticle(String title, String body);
	
	public List<Article> getArticles();
	
	public Article getArticleById(int id);
	
	public void deleteArticle(Article article);
	
	public void modifyArticle(Article article, String title, String body);
}

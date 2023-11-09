package com.koreaIT.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.koreaIT.demo.vo.Article;

@Component
public class ArticleDao {
	
	private List<Article> articles;
	private int lastArticleId;
	
	public ArticleDao() {
		this.articles = new ArrayList<>();
		this.lastArticleId = 0;
		
		makeTestData();
	}
	
	public void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			writeArticle("내용 " + i,"내용 " + i);
		}
	}

	public Article writeArticle(String title, String body) {
		Article article = new Article(++this.lastArticleId,title,body);
		
		this.articles.add(article);
		
		return article;
	}

	public List<Article> getArticles() {
		return this.articles;
	}
	
	public Article getArticleById(int id) {
		for(Article article : this.articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	public void deleteArticle(Article article) {
		this.articles.remove(article);
	}
	

	public void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
}

package com.koreaIT.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreaIT.demo.dao.ArticleDao;
import com.koreaIT.demo.vo.Article;

@Service
public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void writeArticle(int memberId,int boardId, String title, String body) {
		articleDao.writeArticle(memberId,boardId,title,body);
	}

	public List<Article> getArticles(int boardId, int boardPage) {
		boardPage = (boardPage-1)*10;
		return articleDao.getArticles(boardId, boardPage);
	}
	
	public int getArticleCntById(int boardId) {
		return articleDao.getArticleCntById(boardId);
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}

	public Article forPrintArticle(int id) {
		return articleDao.forPrintArticle(id);
	}

	public int getLastArticleId() {
		return articleDao.getLastArticleId();
	}

}

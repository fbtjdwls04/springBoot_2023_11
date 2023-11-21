package com.koreaIT.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreaIT.demo.dao.ArticleDao;
import com.koreaIT.demo.dao.util.Util;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.ResultData;

@Service
public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public void writeArticle(int memberId,int boardId, String title, String body) {
		articleDao.writeArticle(memberId,boardId,title,body);
	}

	public List<Article> getArticles(int boardId, int startLimit, int itemsInAPage, String searchType, String searchMsg) {
		return articleDao.getArticles(boardId, startLimit, itemsInAPage, searchType, searchMsg);
	}

	public int getArticleCntById(int boardId, String searchType, String searchMsg) {
		return articleDao.getArticleCntById(boardId, searchType, searchMsg);
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

	public ResultData<Integer> increaseHitCount(int id) {
		
		int affectedRowsCnt = articleDao.increaseHitCount(id);
		
		if (affectedRowsCnt == 0) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id), affectedRowsCnt);
		}
		
		return ResultData.from("S-1", "조회수 증가", affectedRowsCnt);
	}

	
	public int getLastArticleId() {
		return articleDao.getLastArticleId();
	}

	public int getArticleHitCount(int id) {
		return articleDao.getArticleHitCount(id);
	}


}

package com.koreaIT.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.dao.util.Util;
import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.ResultData;

@Controller
public class UsrHomeArticleController {
	
	private ArticleService articleService;
	
	public UsrHomeArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(String title, String body) {
		
		if(Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if(Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		articleService.writeArticle(title, body);
		
		int id  = articleService.getLastInsertId();
		
		Article article = articleService.getArticleById(id);
		
		return ResultData.from("S-1", Util.f("%d번 게시글을 생성했습니다", id), article);
	}
	
	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public ResultData<List<Article>> showList() {
		
		List<Article> articles = articleService.getArticles();
		
		if(articles.size() == 0) {
			return ResultData.from("F-1","게시물이 존재하지 않습니다");
		}
		
		return ResultData.from("S-1","게시물 목록", articles);
	}
	
	@RequestMapping("/usr/article/showDetail")
	@ResponseBody
	public ResultData<Article> showDetail(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		return ResultData.from("S-1", Util.f("%d번 게시물 조회", id), article);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		articleService.modifyArticle(id, title, body);
		
		return ResultData.from("S-1", Util.f("%d번 게시물 수정 성공", id));
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		articleService.deleteArticle(id);
		
		return ResultData.from("S-1", Util.f("%d번 게시물 삭제 성공", id));
	}
	
}
package com.koreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.vo.Article;

@Controller
public class UsrHomeArticleController {
	
	private ArticleService articleService;
	
	public UsrHomeArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/showDetail")
	@ResponseBody
	public String showDetail(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>",id);
		}
		
		return String.format("번호 : %d <br/> 제목 : %s <br/> 내용 : %s",article.getId(), article.getTitle(), article.getBody());
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		
		articleService.writeArticle(title, body);
		
		int id  = articleService.getLastInsertId();
		
		Article article = articleService.getArticleById(id);
		
		return article;
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>",id);
		}
		
		articleService.modifyArticle(id, title, body);
		
		return String.format("<script>alert('%d번 게시물이 수정 되었습니다.'); location.replace('showList');</script>",id);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>",id);
		}
		
		articleService.deleteArticle(id);
		
		return String.format("<script>alert('%d번 게시물이 삭제 되었습니다.'); location.replace('showList');</script>",id);
	}
	
}
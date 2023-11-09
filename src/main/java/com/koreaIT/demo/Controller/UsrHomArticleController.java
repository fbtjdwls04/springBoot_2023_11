package com.koreaIT.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class UsrHomArticleController {
	
	List<Article> articles;
	
	int lastArticleId;
	int testcaseCnt = 10;
	
	UsrHomArticleController(){
		this.articles = new ArrayList<>();
		this.lastArticleId = 0;
		makeTestData();
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		Article article = writeArticle(title, body);
		
		return article;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = getArticleById(id);
		
		if(article == null) {
			return "<script>alert('해당 게시물은 존재하지 않습니다.'); location.replace('showList');</script>";
		}
		
		deleteArticle(article);
		
		return String.format("<script>alert('%d번 게시물이 삭제 되었습니다.'); location.replace('showList');</script>",id);
	}
	
	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return getArticles();
	}
	
	//============================================== 기능 메서드 =======================================
	private void makeTestData() {
		for(int i = 1; i <= testcaseCnt; i++) {
			writeArticle("내용 " + i,"내용 " + i);
		}
	}

	private Article writeArticle(String title, String body) {
		Article article = new Article(++this.lastArticleId,title,body);
		this.articles.add(article);
		
		return article;
	}

	private Article getArticleById(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	private void deleteArticle(Article article) {
		this.articles.remove(article);
	}
	
	private List<Article> getArticles() {
		return this.articles;
	}
}

@Data
@AllArgsConstructor
class Article {
	private int id = 1;
	private String title;
	private String body;
}
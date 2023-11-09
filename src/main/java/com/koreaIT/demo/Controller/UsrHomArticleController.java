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
	
	private Article writeArticle(int id, String title, String body) {
		Article article = new Article(id,title,body);
		articles.add(article);
		
		return article;
	}
	
	private void makeTestData() {
		for(int i = 1; i <= testcaseCnt; i++) {
			writeArticle(++this.lastArticleId,"내용 " + i,"내용 " + i);
		}
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		Article article = writeArticle(++this.lastArticleId, title, body);
		
		return article;
	}
	
	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return this.articles;
	}
}

@Data
@AllArgsConstructor
class Article {
	int id = 1;
	String title;
	String body;
}
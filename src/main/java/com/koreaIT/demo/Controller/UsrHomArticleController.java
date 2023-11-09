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
	
	UsrHomArticleController(){
		this.articles = new ArrayList<>();
		this.lastArticleId = 0;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		
		this.lastArticleId++;
		
		Article article = new Article(this.lastArticleId,title,body);
		
		articles.add(article);
		
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
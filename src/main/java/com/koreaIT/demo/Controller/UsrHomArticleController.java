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

	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return getArticles();
	}
	
	@RequestMapping("/usr/article/showDetail")
	@ResponseBody
	public String showDetail(int id) {
		
		Article article = getArticleById(id);
		
		if(article == null) {
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>",id);
		}
		
		return String.format("번호 : %d <br/> 제목 : %s <br/> 내용 : %s",article.getId(), article.getTitle(), article.getBody());
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		
		Article article = writeArticle(title, body);
		
		return article;
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = getArticleById(id);
		
		if(article == null) {
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>",id);
		}
		
		modifyArticle(article, title, body);
		
		return String.format("<script>alert('%d번 게시물이 수정 되었습니다.'); location.replace('showList');</script>",id);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = getArticleById(id);
		
		if(article == null) {
			return String.format("<script>alert('%d번 게시물은 존재하지 않습니다.'); location.replace('showList');</script>",id);
		}
		
		deleteArticle(article);
		
		return String.format("<script>alert('%d번 게시물이 삭제 되었습니다.'); location.replace('showList');</script>",id);
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

	private List<Article> getArticles() {
		return this.articles;
	}
	
	private Article getArticleById(int id) {
		
		for(Article article : this.articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	private void deleteArticle(Article article) {
		this.articles.remove(article);
	}
	

	private void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
}

@Data
@AllArgsConstructor
class Article {
	private int id = 1;
	private String title;
	private String body;
}
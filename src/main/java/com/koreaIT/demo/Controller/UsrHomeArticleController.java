package com.koreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.dao.util.Util;
import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.ResultData;
import com.koreaIT.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrHomeArticleController {
	
	private ArticleService articleService;
	
	public UsrHomeArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req,String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}
		
		if(Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		articleService.writeArticle(rq.getLoginedMemberId(),title, body);
		
		int id  = articleService.getLastInsertId();
		
		return Util.jsReplace(Util.f("%d번 게시물이 작성되었습니다",id),Util.f("detail?id=%d",id));
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles",articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req,Model model,int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.forPrintArticle(id);
		
		if(article == null) {
			return "redirect:/usr/article/list";
		}
		
		model.addAttribute("article",article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public String modify(HttpSession session, int id, String title, String body) {
		
		if(session.getAttribute("loginedMemberId") == null) {
			return "<script>alert('로그인 후 사용이 가능합니다'); location.replace('list');</script>";
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return "<script>alert('게시글이 존재하지 않습니다'); location.replace('list');</script>";
		}
		
		if((int)session.getAttribute("loginedMemberId") != article.getMemberId()) {
			return "<script>alert('권한이 없습니다'); location.replace('list');</script>";
		}
		
		articleService.modifyArticle(id, title, body);
		
		return Util.f("<script>alert('%d번 게시글이 수정되었습니다'); location.replace('detail?id=%d');</script>",id,id);
	}
	
	@RequestMapping("/usr/article/doModify")
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return "<script>alert('게시글이 존재하지 않습니다'); location.replace('list');</script>";
		}
		
		if(rq.getLoginedMemberId() != article.getMemberId()) {
			return "<script>alert('권한이 없습니다'); location.replace('list');</script>";
		}
		
		articleService.modifyArticle(id, title, body);
		
		return Util.f("<script>alert('%d번 게시글이 수정되었습니다'); location.replace('detail?id=%d');</script>",id,id);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);

		if(article == null) {	
			return Util.jsHistoryBack(Util.f("%d번 게시글은 존재하지 않습니다",id));
		}
		
		if(rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsHistoryBack(Util.f("권한이 없습니다.",id));
		}
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(Util.f("%d번 게시물을 삭제하였습니다",id),"list");
	}
	
}
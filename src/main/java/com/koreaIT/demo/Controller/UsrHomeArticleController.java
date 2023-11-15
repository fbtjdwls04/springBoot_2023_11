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

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrHomeArticleController {
	
	private ArticleService articleService;
	
	public UsrHomeArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpSession session,String title, String body) {
		
		if(session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L","로그인 후 사용해주세요");
		}
		
		if(Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if(Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		int memberId = (int)session.getAttribute("loginedMemberId");
		articleService.writeArticle(memberId,title, body);
		
		int id  = articleService.getLastInsertId();
		
		Article article = articleService.getArticleById(id);
		
		return ResultData.from("S-1", Util.f("%d번 게시글을 생성했습니다", id), article);
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		
		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles",articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpSession session,Model model,int id) {
		
		Article article = articleService.forPrintArticle(id);
		
		int loginedMemberId = -1;
		
		if(article == null) {
			return "redirect:/usr/article/list";
		}
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		}
		
		model.addAttribute("article",article);
		model.addAttribute("loginedMemberId", loginedMemberId);
		
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
	public String doModify(HttpSession session, int id, String title, String body) {
		
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
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String doDelete(HttpSession session, int id) {
		
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
		
		articleService.deleteArticle(id);
		
		return Util.f("<script>alert('%d번 게시글이 삭제되었습니다'); location.replace('list');</script>",id);
	}
	
}
package com.koreaIT.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.dao.util.Util;
import com.koreaIT.demo.service.ArticleService;
import com.koreaIT.demo.service.BoardService;
import com.koreaIT.demo.vo.Article;
import com.koreaIT.demo.vo.Board;
import com.koreaIT.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrHomeArticleController {
	
	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;
	
	public UsrHomeArticleController(ArticleService articleService,BoardService boardService,Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/article/write")
	public String write() {
		
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(int boardId, String title, String body) {
		
		articleService.writeArticle(rq.getLoginedMemberId(),boardId,title, body);
		
		int id  = articleService.getLastInsertId();
		
		return Util.jsReplace(Util.f("%d번 게시물이 작성되었습니다",id),Util.f("detail?id=%d",id));
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model, int boardId, int boardPage, String searchType ,String searchMsg) {
		
		Board board = boardService.getBoardById(boardId);
		
		int articleCnt = articleService.getArticleCntById(boardId, searchType, searchMsg);
		int totalPage = (int) Math.ceil((double)articleCnt/10);
		int beginPage = Util.getBeginPage(boardPage);
		int endPage = Util.getEndPage(boardPage);
		
		if(board == null) {
			return rq.jsReturnOnView("존재하지 않는 게시판입니다");
		}
		
		List<Article> articles = articleService.getArticles(boardId, boardPage, searchType, searchMsg);
		
		model.addAttribute("articles",articles);
		model.addAttribute("board",board);
		model.addAttribute("articleCnt",articleCnt);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("boardPage",boardPage);
		model.addAttribute("beginPage",beginPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("searchType",searchType);
		model.addAttribute("searchMsg",searchMsg);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model,int id) {
		
		Article article = articleService.forPrintArticle(id);
		
		if(article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		model.addAttribute("article",article);
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		
		Article article = articleService.forPrintArticle(id);
		
		if(article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시물은 존재하지 않습니다", id));
		}
		
		if(rq.getLoginedMemberId() != article.getMemberId()) {
			return rq.jsReturnOnView("권한이 없습니다");
		}
		
		model.addAttribute("article",article);
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return Util.jsHistoryBack("게시글이 존재하지 않습니다");
		}
		
		if(rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsReplace("권한이 없습니다", Util.f("detail?id=%d", id));
		}
		
		articleService.modifyArticle(id, title, body);
		
		return Util.jsReplace(Util.f("%d번 게시물이 수정되었습니다", id), Util.f("detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {	
			return Util.jsHistoryBack(Util.f("%d번 게시글은 존재하지 않습니다",id));
		}
		
		if(rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsHistoryBack(Util.f("권한이 없습니다.",id));
		}

		int boardId = article.getBoardId();
		
		articleService.deleteArticle(id);
		
		return Util.jsReplace(Util.f("%d번 게시물을 삭제하였습니다",id),Util.f("list?boardId=%d&boardPage=1",boardId));
	}
	
}
package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.dao.util.Util;
import com.koreaIT.demo.service.MemberService;
import com.koreaIT.demo.vo.Member;
import com.koreaIT.demo.vo.ResultData;
import com.koreaIT.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrHomeMemberController {
	
	private MemberService memberService;
	
	public UsrHomeMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@RequestMapping("/usr/member/join")
	public String join(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		return "/usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		
		if(Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		if(Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}
		
		if(Util.empty(nickname)) {
			return Util.jsHistoryBack("닉네임을 입력해주세요");
		}
		
		if(Util.empty(cellphoneNum)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요");
		}
		
		if(Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member != null) {
			return Util.jsHistoryBack(Util.f("%s (은)는 이미 사용중인 아이디입니다", loginId));
		}
		
		memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return Util.jsReplace("회원가입이 완료되었습니다", "/");
	}
	
	@RequestMapping("/usr/member/login")
	public String login(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		
		if(Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		Member member = memberService.doLogin(loginId, loginPw);
		
		if(member == null) {
			return Util.jsHistoryBack("아이디 또는 비밀번호를 확인해주세요");
		}

		req.getSession().setAttribute("loginedMemberId", member.getId());
		
		return Util.jsReplace(Util.f("%s님 환영합니다", member.getLoginId()), "/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		req.getSession().setAttribute("loginedMemberId", 0);
		
		return Util.jsReplace("정상적으로 로그아웃 되었습니다","/");
	}
}
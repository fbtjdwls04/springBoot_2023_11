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

@Controller
public class UsrHomeMemberController {
	
	private MemberService memberService;
	private Rq rq;
	
	public UsrHomeMemberController(MemberService memberService,Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	@RequestMapping("/usr/member/join")
	public String join() {
		
		return "/usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
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
	
	@RequestMapping("/usr/member/loginIdDupChk")
	@ResponseBody
	public ResultData<String> loginIdDupChk(String loginId) {
		
		if(Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member != null) {
			return ResultData.from("F-2",Util.f("%s 은(는) 이미 사용중인 아이디입니다", loginId)); 
		}
		
		return ResultData.from("S-1", "사용 가능한 아이디입니다",loginId);
	}
	
	@RequestMapping("/usr/member/login")
	public String login() {
		
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		
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

		rq.login(member.getId());
		
		return Util.jsReplace(Util.f("%s님 환영합니다", member.getLoginId()), "/");
	}
	
	@RequestMapping("/usr/member/myPage")
	public String showMyPage(int id, Model model) {

		if (rq.getLoginedMemberId() != id) {
			return rq.jsReturnOnView("권한이 없습니다");
		}
		
		Member loginedMember = memberService.getMemberById(id);
		
		model.addAttribute("loginedMember", loginedMember);
		
		return "/usr/member/myPage";
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		
		rq.logout();
		
		return Util.jsReplace("정상적으로 로그아웃 되었습니다","/");
	}
}
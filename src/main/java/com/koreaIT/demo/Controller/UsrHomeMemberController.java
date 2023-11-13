package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.dao.util.Util;
import com.koreaIT.demo.service.MemberService;
import com.koreaIT.demo.vo.Member;
import com.koreaIT.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrHomeMemberController {
	
	private MemberService memberService;
	
	public UsrHomeMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(HttpSession session, String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if(session.getAttribute("loginedMemberId") != null) {
			return ResultData.from("F-L","로그아웃 후 이용해주세요");
		}
		
		if(Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		
		if(Util.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		
		if(Util.empty(name)) {
			return ResultData.from("F-2", "이름을 입력해주세요");
		}
		
		if(Util.empty(nickname)) {
			return ResultData.from("F-2", "닉네임을 입력해주세요");
		}
		
		if(Util.empty(cellphoneNum)) {
			return ResultData.from("F-2", "전화번호를 입력해주세요"); 
		}
		
		if(Util.empty(email)) {
			return ResultData.from("F-2", "이메일을 입력해주세요"); 
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member != null) {
			return ResultData.from("F-7", Util.f("이미 사용중인 아이디(%s)입니다", loginId));
		}
		
		memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return ResultData.from("S-1", "회원 가입 성공", member);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session,String loginId, String loginPw) {
		
		if(session.getAttribute("loginedMemberId") != null) {
			return ResultData.from("F-L","로그아웃 후 이용해주세요");
		}
		
		if(Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		
		if(Util.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		
		Member member = memberService.doLogin(loginId, loginPw);
		
		if(member == null) {
			return ResultData.from("F-3","아이디 또는 비밀번호를 확인해주세요");
		}
		
		session.setAttribute("loginedMemberId", member.getId());
		
		return ResultData.from("S-1",Util.f("%s님 환영합니다", member.getNickname()));
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {
		
		if(session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-L","로그인 후 사용해주세요");
		}
		
		session.removeAttribute("loginedMemberId");
		
		return ResultData.from("S-1","정상적으로 로그아웃 되었습니다");
	}
}
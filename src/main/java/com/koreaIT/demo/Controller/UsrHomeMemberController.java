package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.dao.util.Util;
import com.koreaIT.demo.service.MemberService;
import com.koreaIT.demo.vo.Member;

@Controller
public class UsrHomeMemberController {
	
	private MemberService memberService;
	
	public UsrHomeMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		if(Util.empty(loginId)) {
			return "아이디를 입력해주세요";
		}
		
		if(Util.empty(loginPw)) {
			return  "비밀번호를 입력해주세요";
		}
		
		if(Util.empty(name)) {
			return  "이름을 입력해주세요";
		}
		
		if(Util.empty(nickname)) {
			return  "닉네임을 입력해주세요";
		}
		
		if(Util.empty(cellphoneNum)) {
			return  "전화번호를 입력해주세요";
		}
		
		if(Util.empty(email)) {
			return  "이메일을 입력해주세요";
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member != null) {
			return Util.f("이미 사용중인 아이디(%s)입니다", loginId);
		}
		
		memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return Util.f("<script>alert('%s 계정이 생성되었습니다.'); location.replace('/usr/article/showList');</script>",loginId);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		
		Member member = memberService.doLogin(loginId, loginPw);
		
		if(member == null) {
			return Util.f("<script>alert('아이디와 비밀번호를 확인해주세요.'); location.replace('/usr/article/showList');</script>",loginId);
		}
		
		return Util.f("<script>alert('%s 님 환영합니다!'); location.replace('/usr/article/showList');</script>",loginId);
	}
}
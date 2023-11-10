package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member != null) {
			return String.format("<script>alert('%s 는 사용중인 아이디입니다.'); location.replace('/usr/article/showList');</script>",loginId);
		}
		
		memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return String.format("<script>alert('%s 계정이 생성되었습니다.'); location.replace('/usr/article/showList');</script>",loginId);
	}
}
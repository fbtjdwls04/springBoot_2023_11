package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.dao.util.Util;
import com.koreaIT.demo.service.ReplyService;
import com.koreaIT.demo.vo.Rq;

@Controller
public class UsrHomeReplyController {

	private ReplyService replyService;
	private Rq rq;

	public UsrHomeReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(int relId, String relTypeCode, String body) {
		
		if(body == null) {
			Util.jsReplace("내용을 입력해주세요", Util.f("/usr/article/detail?id=%d", relId));
		}
		
		body = Util.cleanText(body);
		
		replyService.doWrite(relId, relTypeCode,rq.getLoginedMemberId() ,body);
		
		return Util.jsReplace("댓글이 작성되었습니다", Util.f("/usr/article/detail?id=%d", relId));
	}
}
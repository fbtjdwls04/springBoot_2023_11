package com.koreaIT.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreaIT.demo.dao.ReplyDao;
import com.koreaIT.demo.vo.Reply;

@Service
public class ReplyService {

	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public void doWrite(int relId, String relTypeCode,int memberId, String body) {
		replyDao.doWrite(relId, relTypeCode,memberId, body);
	}

	public List<Reply> getReplys(int id, String relTypeCode) {
		return replyDao.getReplys(id, relTypeCode);
	}
}

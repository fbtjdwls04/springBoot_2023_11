package com.koreaIT.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.demo.service.RecommendPointService;
import com.koreaIT.demo.vo.RecommendPoint;
import com.koreaIT.demo.vo.ResultData;
import com.koreaIT.demo.vo.Rq;

@Controller
public class UsrRecommendPointController {
	private RecommendPointService recommendPointService;
	private Rq rq;
	
	public UsrRecommendPointController(RecommendPointService recommendPointService, Rq rq) {
		this.recommendPointService = recommendPointService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/recommendPoint/getRecommendPoint")
	@ResponseBody
	public ResultData<RecommendPoint> getRecommendPoint(String relTypeCode, int relId) {
		return recommendPointService.getRecommendPoint(rq.getLoginedMemberId(), relTypeCode, relId);
	}

	@RequestMapping("/usr/recommendPoint/insertRecommendPoint")
	@ResponseBody
	public String insertRecommendPoint(String relTypeCode, int relId) {

		recommendPointService.insertRecommendPoint(rq.getLoginedMemberId(),relTypeCode, relId);

		return "좋아요 성공";
	}

	@RequestMapping("/usr/recommendPoint/deleteRecommendPoint")
	@ResponseBody
	public String deleteRecommendPoint(String relTypeCode, int relId) {

		recommendPointService.deleteRecommendPoint(rq.getLoginedMemberId(),relTypeCode, relId);

		return "좋아요 취소";
	}
	
}

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

	@RequestMapping("/usr/recommendPoint/doRecommendPoint")
	@ResponseBody
	public boolean doRecommendPoint(String relTypeCode, int relId, boolean recommendBtn) {
		
		if(recommendBtn) {
			recommendPointService.deleteRecommendPoint(rq.getLoginedMemberId(),relTypeCode, relId);
			return false;
		}
		
		recommendPointService.insertRecommendPoint(rq.getLoginedMemberId(),relTypeCode, relId);
		return true;
	}
}

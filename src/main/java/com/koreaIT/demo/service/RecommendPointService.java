package com.koreaIT.demo.service;

import org.springframework.stereotype.Service;

import com.koreaIT.demo.dao.RecommendPointDao;
import com.koreaIT.demo.vo.RecommendPoint;

@Service
public class RecommendPointService {

	private RecommendPointDao recommendPointDao;
	
	public RecommendPointService(RecommendPointDao recommendPointDao) {
		this.recommendPointDao = recommendPointDao;
	}

	public void addRecommend(int id,int memberId) {
		recommendPointDao.addRecommend(id, memberId);
	}
	
	public RecommendPoint getRecommendByMemberId(int id, int memberId) {
		return recommendPointDao.getRecommendByMemberId(id, memberId);
	}

	public void updateRecommend(int id, int memberId, int point) {
		recommendPointDao.updateRecommend(id, memberId, point);
	}
}

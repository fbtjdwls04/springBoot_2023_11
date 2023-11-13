package com.koreaIT.demo.vo;

import lombok.Getter;

public class ResultData {
	
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private Object data;
	
	public static ResultData from(String resultCold, String msg) {
		return from(resultCold, msg, null);
	}
	
	public static ResultData from(String resultCold, String msg, Object data) {
		
		ResultData rd = new ResultData();
		rd.resultCode = resultCold;
		rd.msg = msg;
		rd.data = data;
		
		return rd;
	}
	
	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
}

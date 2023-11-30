<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp" %>
<script>
	async function getData() {
		const API_KEY = "FsMZukSc4pvQ%2BMB5iYsvWC7uwSG2%2Bo4SRG4ymbK1SwNRzcEB%2FzB8qU%2FlebLg%2Bsdle%2BWQ%2Bq1vsmczbmHLGByqiQ%3D%3D";
		
		const url = "http://apis.data.go.kr/3740000/suwonEvChrstn/getdatalist?type=Json&serviceKey=" + API_KEY;
		
		const response = await fetch(url);
		const data = await response.json();
		$('.test').empty().html(data.items[0]);
		console.log(data);
	}
	
	getData();
	
</script>

<div class="test"></div>

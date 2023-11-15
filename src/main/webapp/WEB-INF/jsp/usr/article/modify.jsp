<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE DETAIL" />
	   	
	<%@ include file="../common/head.jsp" %>
   	
	<div class="container mx-auto table-box-style">
		<div>
			<div>번호 : ${article.id}</div>
			<div>작성일 : ${article.regDate.substring(2,16)}</div>
			<div>수정일 : ${article.updateDate.substring(2,16) }</div>
			<div>작성자 : ${article.writerName}</div>
			<div>제목 : ${article.title }</div>
			<div>내용 : ${article.body }</div>
		</div>
		<hr />
		<c:if test="${isDup}">
			<button><a href="modify?id=${article.id }">수정</a></button>
			<button onclick="if(confirm('정말 삭제하시겠습니까?')) location.replace('delete?id=${article.id}');">삭제</button>
		</c:if>
	</div>
	<%@ include file="../common/foot.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE DETAIL" />
	   	
	<%@ include file="../common/head.jsp" %>
   	
	<section class="container mx-auto">
		<table class="table">
			<tr>
				<th>번호</th>
				<td>${article.id}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${article.regDate.substring(2,16)}</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${article.updateDate.substring(2,16) }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${article.writerName}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${article.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td style="white-space: pre;">${article.body }</td>
			</tr>
		</table>
		<hr />
		<div class="flex mt-[20px]">
			<button onclick="history.back()">뒤로가기</button>
			<div class="flex-grow"></div>
			<c:if test="${loginedMemberId == article.memberId }">
				<button class="btn btn-primary mr-4"><a href="modify?id=${article.id }">수정</a></button>
				<button class="btn btn-error" onclick="if(confirm('정말 삭제하시겠습니까?')) location.replace('doDelete?id=${article.id}');">삭제</button>
			</c:if>
		</div>
	</section>
	<%@ include file="../common/foot.jsp" %>
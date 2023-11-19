<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name } 게시판" />

<%@ include file="../common/head.jsp"%>

<section class="container mx-auto">
	<p><c:if test="${searchType != null and searchType != ''}">${searchMsg } (으)로 검색된 </c:if>게시물 수 : ${articleCnt }</p>
	<table class="table text-[16px] text-center">
		<thead>
			<tr>
				<th width="150">번호</th>
				<th>제목</th>
				<th width="150">작성자</th>
				<th width="200">작성일</th>
			</tr>
		</thead>
		<tbody>
			<!-- 게시물 리스트 시작 -->
			<c:forEach var="article" items="${articles}">
				<tr>
					<td>${article.id}</td>
					<td class="hover:underline"><a href="detail?id=${article.id}">${article.title}</a></td>
					<td>${article.writerName}</td>
					<td>${article.regDate.substring(2,16)}</td>
				</tr>
			</c:forEach>
			<!-- 게시물 리스트 끝 -->
		</tbody>
	</table>
	
	<c:if test="${articleCnt == 0}">
		<p class="text-[16px] text-center mt-4">게시물이 없습니다.</p]>
	</c:if>
	
	<div class="flex justify-end">
		<a class="px-2 hover:underline btn" href="write">글쓰기</a>
	</div>

	<!-- 페이지 리스트 시작 -->
	<div class="flex justify-center items-center flex-wrap">
		
		<c:if test="${beginPage > 1}">
			<a class="text-2xl" href="list?boardId=${board.id }&boardPage=${beginPage-10}&searchType=${searchType}&searchMsg=${searchMsg}"> 
				<i class="fa-solid fa-caret-left"></i>
			</a>
		</c:if>

		<c:forEach var="i" begin="${beginPage }" end="${endPage }" step="1">
			<c:if test="${i <= totalPage }">
				<a
					class="mx-2 hover:underline <c:if test="${i == boardPage }">text-2xl bg-gray-200</c:if>"
					href="list?boardId=${board.id }&boardPage=${i}&searchType=${searchType}&searchMsg=${searchMsg}">${i}</a>
			</c:if>
		</c:forEach>

		<c:if test="${endPage < totalPage }">
			<a class="text-2xl" href="list?boardId=${board.id }&boardPage=${beginPage+10}&searchType=${searchType}&searchMsg=${searchMsg}">
				<i class="fa-solid fa-caret-right"></i>
			</a>
		</c:if>
	</div>
	<!-- 페이지 리스트 끝 -->
	<!-- 검색창 시작-->
	<form action="list">
		<div class="flex justify-center mt-4">
			<input type="hidden" name="boardId" value="${board.id }"/>
			<input type="hidden" name="boardPage" value="1"/>
			<select class="max-w-xs border mr-4" name="searchType" >
			  	<option value="title" <c:if test="${searchType == 'title' }">selected="selected"</c:if>>제목</option>
			  	<option value="body" <c:if test="${searchType == 'body' }">selected="selected"</c:if>>내용</option>
			  	<option value="writerName" <c:if test="${searchType == 'writerName' }">selected="selected"</c:if>>작성자</option>
			</select>
			<input class="input input-bordered w-full max-w-xs" type="text" name="searchMsg" value="${searchMsg }"/>
			<button class="btn ml-2">검색</button>
		</div>
	</form>
	<!-- 검색창 끝-->
</section>

<%@ include file="../common/foot.jsp"%>
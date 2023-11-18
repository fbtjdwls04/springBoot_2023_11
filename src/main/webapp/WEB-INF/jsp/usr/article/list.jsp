<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name } 게시판" />

<%@ include file="../common/head.jsp"%>

<section class="container mx-auto">
	<p>게시물 수 : ${articleCnt }</p>
	<table class="table text-[16px] text-center">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성일</th>
				<th>제목</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
				<tr>
					<td>${article.id}</td>
					<td>${article.regDate.substring(2,16)}</td>
					<td class="hover:underline"><a href="detail?id=${article.id}">${article.title}</a></td>
					<td>${article.writerName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="flex justify-end">
		<a class="px-2 hover:underline btn" href="write">글쓰기</a>
	</div>

	<div class="flex justify-center items-center flex-wrap">

		<c:if test="${beginPage > 1}">
			<a class="text-2xl" href="list?boardId=${board.id }&boardPage=${beginPage-10}"> 
				<i class="fa-solid fa-caret-left"></i>
			</a>
		</c:if>

		<c:forEach var="i" begin="${beginPage }" end="${endPage }" step="1">
			<c:if test="${i <= totalPage }">
				<a
					class="mx-2 hover:underline <c:if test="${i == boardPage }">text-2xl bg-gray-200</c:if>"
					href="list?boardId=${board.id }&boardPage=${i}">${i}</a>
			</c:if>
		</c:forEach>

		<c:if test="${endPage < totalPage }">
			<a class="text-2xl" href="list?boardId=${board.id }&boardPage=${beginPage+10}">
				<i class="fa-solid fa-caret-right"></i>
			</a>
		</c:if>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>
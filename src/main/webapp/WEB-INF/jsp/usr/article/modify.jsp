<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE" />

<%@ include file="../common/head.jsp"%>
<script>
	function modifySubmit(e) {

		if (e.title.value.trim().length == 0) {
			alert('제목을 입력해주세요');
			e.title.focus();
			return;
		}

		if (e.body.value.trim().length == 0) {
			alert('내용을 입력해주세요');
			e.body.focus();
			return;
		}

		e.submit();
	}
</script>

<section class="container mx-auto">
	<form action="doModify"
		onsubmit="modifySubmit(this); return false;" method="post">
		<input type="hidden" name="id" value="${article.id }" />
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
				<td><input class="input input-bordered w-full max-w-xs" type="text" name="title"
					placeholder="제목을 입력해주세요" value="${article.title}" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea class="textarea textarea-bordered" name="body" id="" cols="30"
						rows="10" placeholder="내용을 입력해주세요">${article.body}</textarea></td>
			</tr>
		</table>
		<div class="flex justify-end mr-4">
			<button class="btn">수정</button>
		</div>
	</form>
</section>
<%@ include file="../common/foot.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE WRITE" />
	   	
	<%@ include file="../common/head.jsp" %>
   	<script>
   		function writeSubmit(e) {
			
   			if(e.title.value.trim().length == 0){
   				alert('제목을 입력해주세요');
   				e.title.focus();
   				return;
   			}

   			if(e.body.value.trim().length == 0){
   				alert('내용을 입력해주세요');
   				e.body.focus();
   				return;
   			}
   			
   			e.submit();
		}
   	</script>
   	
	<section class="container mx-auto flex justify-center">
		<form action="doWrite" onsubmit="writeSubmit(this); return false;" method="post">
			<table class="table">
				<tr>
					<th>게시판</th>
					<td class="flex">
						<label class="mr-4 flex items-center">
							<input name="boardId" type="radio" value="1" <c:if test="${rq.getLoginedMemberId() != 1}">disabled</c:if>/>
							&nbsp;&nbsp;공지사항
						</label>
						<label class="flex items-center">
							<input name="boardId" type="radio" value="2" checked/>
							&nbsp;&nbsp;자유게시판
						</label>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input name="title" class="input input-bordered w-full max-w-xs" type="text" placeholder="제목을 입력해주세요"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="body" class="textarea textarea-bordered" id="" cols="30" rows="10" placeholder="내용을 입력해주세요"></textarea></td>
				</tr>
			</table>
			<div class="flex justify-end">
				<button class="btn btn-success text-[17px]">작성</button>
			</div>
		</form>
	</section>
	
	<%@ include file="../common/foot.jsp" %>
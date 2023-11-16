<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="ARTICLE WRITE" />
	   	
	<%@ include file="../common/head.jsp" %>
   	<script>
   		function modifySubmit(e) {
			
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
   	
	<div class="container mx-auto table-box-style">
		<form class="border flex flex-col" action="doModify" onsubmit="modifySubmit(this); return false;" method="post">
			<input type="hidden" name="id" value="${article.id }"/>
			<input class="border" type="text" name="title" placeholder="제목을 입력해주세요" value="${article.title}"/>
			<textarea class="border" name="body" id="" cols="30" rows="10" placeholder="내용을 입력해주세요" >${article.body}</textarea>
			<button>수정</button>
		</form>
	</div>
	<%@ include file="../common/foot.jsp" %>
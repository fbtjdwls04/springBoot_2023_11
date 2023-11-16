<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="MEMBER LOGIN" />
	   	
	<%@ include file="../common/head.jsp" %>
   	<script>
   		function loginSubmit(e) {
   			
   			if(e.loginId.value.trim().length == 0){
   				alert('아이디를 입력해주세요');
   				e.loginId.focus();
   				return;
   			}

   			if(e.loginPw.value.trim().length == 0){
   				alert('비밀번호를 입력해주세요');
   				e.loginPw.focus();
   				return;
   			}
   			
   			e.submit();
		}
   	</script>
   	
	<div class="container flex flex-col items-center mx-auto">
		<form class="border flex flex-col" action="doLogin" onsubmit="loginSubmit(this); return false;" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="loginId" placeholder="아이디를 입력해주세요" autocomplete="off"/></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="loginPw" placeholder="비밀번호를 입력해주세요"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<button>로그인</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="../common/foot.jsp" %>
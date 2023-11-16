<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="MEMBER JOIN" />
	   	
	<%@ include file="../common/head.jsp" %>
   	
   	<script>
   		function joinSubmit(e) {
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
   			if(e.loginPw.value.trim() != e.pwChk.value.trim()){
   				alert('비밀번호가 다릅니다');
   				e.pwChk.focus();
   				return;
   			}
   			if(e.name.value.trim().length == 0){
   				alert('이름을 입력해주세요');
   				e.name.focus();
   				return;
   			}
   			if(e.nickname.value.trim().length == 0){
   				alert('닉네임을 입력해주세요');
   				e.nickname.focus();
   				return;
   			}
   			//수정 필요
   			if(e.cellphoneNum.value.trim().length == 0){
   				alert('전화번호를 입력해주세요');
   				e.cellphoneNum.focus();
   				return;
   			}
   			if(e.email.value.trim().length == 0){
   				alert('이메일을 입력해주세요');
   				e.email.focus();
   				return;
   			}
   			
   			e.submit();
		}
   	</script>
   	
	<div class="container flex flex-col items-center mx-auto">
		<form action="doJoin" onsubmit="joinSubmit(this); return false;" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="loginId" autocomplete="off"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="loginPw" /></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="pwChk" /></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" autocomplete="off"></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" name="nickname" autocomplete="off"/></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" name="cellphoneNum" autocomplete="off"/></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" autocomplete="off"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="w-full text-center">가입완료</button>
					</td>
				</tr>
			</table>
			
		</form>
	</div>
	<%@ include file="../common/foot.jsp" %>
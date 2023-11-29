<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="MEMBER JOIN" />
	   	
	<%@ include file="../common/head.jsp" %>
   	
   	<script>
   		const joinSubmit = function(e) {
   			
   			e.loginId.value = e.loginId.value.trim();
   			e.loginPw.value = e.loginPw.value.trim();
   			e.pwChk.value = e.pwChk.value.trim();
   			e.name.value = e.name.value.trim();
   			e.nickname.value = e.nickname.value.trim();
   			e.cellphoneNum.value = e.cellphoneNum.value.trim();
   			e.email.value = e.email.value.trim();
   			
   			if(e.loginId.value.length == 0){
   				alert('아이디를 입력해주세요');
   				e.loginId.focus();
   				return;
   			}
   			if(e.loginPw.value.length == 0){
   				alert('비밀번호를 입력해주세요');
   				e.loginPw.focus();
   				return;
   			}
   			if(e.loginPw.value != e.pwChk.value){
   				alert('비밀번호가 다릅니다');
   				e.pwChk.focus();
   				return;
   			}
   			if(e.name.value.length == 0){
   				alert('이름을 입력해주세요');
   				e.name.focus();
   				return;
   			}
   			if(e.nickname.value.length == 0){
   				alert('닉네임을 입력해주세요');
   				e.nickname.focus();
   				return;
   			}
   			//수정 필요
   			if(e.cellphoneNum.value.length == 0){
   				alert('전화번호를 입력해주세요');
   				e.cellphoneNum.focus();
   				return;
   			}
   			if(e.email.value.length == 0){
   				alert('이메일을 입력해주세요');
   				e.email.focus();
   				return;
   			}
   			
   			//수정 필요
   			e.submit();
		}
   		
   		const loginIdDupChk = function(e, name) {
			e.value = e.value.trim();
			let chkMsg = $(e).next();
			
			if(e.value.length == 0){
				chkMsg.addClass('text-red-500');
				chkMsg.html('<span>' + name + ' 은(는) 필수 입력 정보입니다</span>');
			}else{
				chkMsg.removeClass('text-red-500');
				chkMsg.html('');
			}
		}
   	</script>
   	
	<section class="flex justify-center">
		<form action="doJoin" onsubmit="joinSubmit(this); return false;" method="post">
			<table class="table text-center border">
				<tr>
					<th>아이디</th>
					<td>
						<input class="input input-bordered w-full" type="text" name="loginId" autocomplete="off" onblur="loginIdDupChk(this, '아이디');">
						<div class="h-2 w-[265px]"></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input class="input input-bordered w-full" type="password" name="loginPw" onblur="loginIdDupChk(this, '비밀번호');" />
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>
						<input class="input input-bordered w-full" type="password" name="pwChk" onblur="loginIdDupChk(this, '비밀번호 확인');" />
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input class="input input-bordered w-full" type="text" name="name" autocomplete="off" onblur="loginIdDupChk(this, '이름');">
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td>
						<input class="input input-bordered w-full" type="text" name="nickname" autocomplete="off" onblur="loginIdDupChk(this, '닉네임');"/>
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input class="input input-bordered w-full" type="tel" name="cellphoneNum" autocomplete="off" onblur="loginIdDupChk(this, '전화번호');"/>
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input class="input input-bordered w-full" type="email" name="email" autocomplete="off" onblur="loginIdDupChk(this, '이메일');"/>
						<div class="h-2"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="p-0">
						<button class="hover:bg-gray-200 w-full text-center p-4 font-bold">가입완료</button>
					</td>
				</tr>
			</table>
			
		</form>
	</section>
	
	<%@ include file="../common/foot.jsp" %>
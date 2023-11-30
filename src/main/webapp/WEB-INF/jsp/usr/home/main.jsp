<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   	
   	<c:set var="pageTitle" value="MAIN" />
   	
   	<%@ include file="../common/head.jsp" %>
   	
   	<section class="container mx-auto">
		<div class="text-center">
			<img class="mx-auto w-[800px] rounded-[20px]" src="/resource/images/funkinCat.png" alt="" />
			<br />
			<p class="text-4xl font-bold">Hi! Welcome! This is <span style="color: blue;">Spring boot</span> practice page</p>
		</div>
		
		<div class="flex">
			<div class="modal-exam"><span>모달예시</span></div>
			<div class="w-[100px]"></div>
			<div class="popUp-exam"><span>팝업예시</span></div>
		</div>
		
		<div class="layer-bg"></div>
		<div class="layer">
			<h1>modal</h1>
			<div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Possimus tenetur adipisci distinctio delectus culpa voluptatibus quaerat fuga explicabo natus quisquam. Labore aut sit officia illo velit molestiae placeat ipsa repellendus.</div>
			<button class="close-btn-x">&times;</button>
			<button class="close-btn btn btn-primary">close</button>
		</div>
   	</section>
	
	<%@ include file="../common/foot.jsp" %>
	

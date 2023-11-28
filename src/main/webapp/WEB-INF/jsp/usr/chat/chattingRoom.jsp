<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="CHAT" />
   	
<%@ include file="../common/head.jsp" %>

	<script>
	$(function(){
		let ws = new WebSocket("ws://localhost:8081/chat");
		   
		$("#chatInput").on("keydown", function(e){
			if(e.keyCode == 13){
				let text = $("#chatInput").val(); 
				let line = $("<div>"); 
				line.append(text); 
				
				$("#chatInput").val(""); 
				ws.send(text);
				
				return false; 
			}
		});
		
		ws.onmessage  = function(e){
			let line = $("<div>");
			line.append(e.data);
			console.log(e)
			$("#chat-box").append(line);
		}
	})
		
	</script>

	<section class="flex justify-center">
		<div class="container border">
			<h1 class="text-center">채팅방</h1>
			<div id="chat-box" class="h-[600px]">
			
			</div>
			<div class="flex">
				<input id="chatInput" type="text" class="w-full input input-bordered"/>
				<button id="sendBtn" class="w-[100px] btn">전송</button>
			</div>
		</div>	
	</section>
	
   	
<%@ include file="../common/foot.jsp" %>
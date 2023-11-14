<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 테일 윈드 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"/>
<!-- jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" ></script>
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css" />
<title>${pageTitle}</title>
</head>
<body>
	<div>
		<div class="flex justify-center text-4xl"><a href="/">로고</a></div>
		
		<ul class="flex">
			<li class="hover:underline"><a href="/">home</a></li>
			<li class="hover:underline"><a href="/usr/article/list">LIST</a></li>
		</ul>
	</div>
	
	<section>
		<div>
			<h1 class="flex justify-center text-2xl">${pageTitle }&nbsp;PAGE</h1>
		</div>
	</section>

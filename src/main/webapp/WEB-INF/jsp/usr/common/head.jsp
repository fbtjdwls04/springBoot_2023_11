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
	<div class="p-4 flex container mx-auto items-center">
		<div class="h-full items-center text-4xl"><a href="/"><i class="fa-solid fa-code"></i></a></div>
		<div class="flex-grow"></div>
		<ul class="flex text-2xl">
			<li class="hover:underline"><a class="px-4" href="/">HOME</a></li>
			<li class="hover:underline"><a class="px-4" href="/usr/article/list">LIST</a></li>
		</ul>
	</div>
	
	<section class="container mx-auto mb-20">
		<div>
			<h1 class="flex justify-center text-3xl">${pageTitle }&nbsp;PAGE</h1>
		</div>
	</section>

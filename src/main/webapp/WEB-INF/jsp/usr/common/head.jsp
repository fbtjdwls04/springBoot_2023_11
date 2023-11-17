<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html data-theme="cupcake">
<head>
<meta charset="UTF-8">
<!-- 테일 윈드, daisyUI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.0.8/dist/full.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<!-- jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" ></script>
<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css" />
<title>${pageTitle}</title>
</head>
<body>
	<header class="fixed w-full bg-base-300 z-10">
		<div class="p-4 flex container mx-auto items-center">
			<div class="h-full items-center text-4xl"><a href="/"><i class="fa-solid fa-code"></i></a></div>
			<div class="grow"></div>
			<ul class="flex text-2xl">
				<li class="hover:underline"><a class="px-4" href="/">HOME</a></li>
				<li class="hover:underline"><a class="px-4" href="/usr/article/list">LIST</a></li>
				<c:if test="${rq.getLoginedMemberId() == 0 || rq.getLoginedMemberId() == null }">
					<li class="hover:underline"><a class="px-4" href="/usr/member/login">LOGIN</a></li>
					<li class="hover:underline"><a class="px-4" href="/usr/member/join">JOIN</a></li>
				</c:if>
				<c:if test="${rq.getLoginedMemberId() != 0 && rq.getLoginedMemberId() != null}">
					<li class="hover:underline"><a class="px-4" href="/usr/article/write">WRITE</a></li>
					<li class="hover:underline"><a class="px-4" href="/usr/member/doLogout">logout</a></li>
				</c:if>
			</ul>
		</div>
	</header>
	<div class="h-[100px]"></div>
	<section class="container mx-auto mb-[25px]">
		<div>
			<h1 class="flex justify-center text-3xl">${pageTitle }&nbsp;PAGE</h1>
		</div>
	</section>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admincss/style.css">
</head>
<body>
<div class="container">

		<h1>以下の商品をショッピングカートから削除しました。</h1>
		<br />
		<br />

		${dto.itemsDTO.name }<br />
		${dto.itemsDTO.manufacturer }<br />
		${dto.itemsDTO.price * dto.amount}円<br />
		数量 ${dto.amount} 個<br />

		<a href="/brmy/MainController"class ="styled-link">商品検索</a>へ<br />
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

		<h3>以下の商品をショッピングカートから削除してよろしいですか？</h3>
		<br />
		<br />

		${dto.itemsDTO.name }<br />
		${dto.itemsDTO.manufacturer }<br />
		${dto.itemsDTO.price * dto.amount}円<br />
		数量 ${dto.amount} 個<br />

		<form action="/brmy/RemoveFromCartCommitController"" method="POST">
			<input type="hidden" name="itemId" value="${itemId }" />
			<input type="submit" value="削除する" /><br />
		</form>
		<a href="/brmy/main">商品検索</a>へ<br />

</body>
</html>
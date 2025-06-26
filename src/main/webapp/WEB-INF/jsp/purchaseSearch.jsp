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
<body class="center">
<div class="container">
<h1>購入履歴</h1>
<table class ="styled-table">
<thead>
<tr>
	<th>購入者ID</th><th>注文日</th><th>購入商品</th><th>配送先</th><th</th>
</tr>
</thead>
<c:forEach var="purchase" items="${purchas }">
<c:if test="${purchase.cansel == false}">
<tr>
	<td>${purchase.purchaseId }</td><td>${purchase.purchaseDate }</td>
	<td>
		<table class ="styled-table">
		<tr>
			<th>商品名</th><th>色</th><th>メーカー</th><th>単価</th><th>数量</th>
		</tr>
		<c:forEach var="detail" items="${purchase.purchaseDetailsDTO }">
		<tr>
			<td>${detail.itemsDTO.name }</td><td>${detail.itemsDTO.color}</td>
			<td>${detail.itemsDTO.manufacturer}</td><td>${detail.itemsDTO.price}円</td>
			<td>${detail.amount}個</td>
		</tr>
		</c:forEach>
		</table>
	</td>
	<td>${purchase.destination }</td>
	<td><a href="/brmy/DeletePurchaseConfirmController?purchaseId=${purchase.purchaseId }"class ="styled-link">
			キャンセル </a></td>
</tr>
	</c:if>
	</c:forEach>
</table>

<a href="/brmy/AdminMainScreen"class ="styled-link">管理者メインページへ戻る </a>


</div>
</body>
</html>
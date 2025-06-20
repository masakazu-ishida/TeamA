<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>以下の注文をキャンセルしてよろしいですか？</h1>
<form action="/brmy/DeletePurchaseCommitController" method="get">
<table border="1">
<tr>
	<th>購入者ID</th><td>${dto.purchaseId }</td>
</tr>
<tr>
	<th>注文日</th><td>${dto.purchaseDate }</td>
</tr>
<tr>
	<th>購入商品</th><td>	<table border="1">
								<tr>
								<th>商品名</th><th>色</th><th>メーカー</th><th>単価</th><th>数量</th>
								</tr>
								<c:forEach var="detail" items="${dto.purchaseDetailsDTO }">
								<tr>
								<td>${detail.itemsDTO.name }</td><td>${detail.itemsDTO.color}</td>
								<td>${detail.itemsDTO.manufacturer}</td><td>${detail.itemsDTO.price}</td>
								<td>${detail.amount}</td>
								</tr>
								</c:forEach>
							</table>
					 </td>
<tr>
	<th>配送先</th><td>${dto.destination}</td>
</tr>
<tr>
	<td colspan="2"><input type="submit" value="キャンセル"></td>
</tr>
</table>
<input type="hidden" name="purchaseId" value="${dto.purchaseId }" 
</form>

<a href="/brmy/AdminMainScreen">管理者メインページへ戻る </a>

</body>
</html>
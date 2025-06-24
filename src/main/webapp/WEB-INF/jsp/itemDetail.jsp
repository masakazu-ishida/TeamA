<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h3>商品の詳細表示</h3><br>
<c:if test="${flg=='flg' }">すでにカートに存在します</c:if>

<table border="1">

<tr>
<th>商品名</th><td>${itemsDto.name}</td>
</tr>

<tr>
<th>商品の色</th><td>${itemsDto.color}</td>
</tr>

<tr>
<th>メーカー名</th><td>${itemsDto.manufacturer}</td>
</tr>

<tr>
<th>価格</th><td>${itemsDto.price}円</td>
</tr>

<tr>
<th>在庫数</th><td>${itemsDto.stock}個</td>
</tr>

</table><br>


<form action = "/brmy/CartAddController"><select name ="amount">
数量<option value = "1" selected>1</option>
<option value = "2">2</option>
<option value = "3">3</option>
<option value = "4">4</option>
<option value = "5">5</option></select><br>
<input type="submit" value="ショッピングカートに入れる">
<input type="hidden" value="${itemsDto.itemId}" name= "itemId">
</form>


<br>
<a href = "/brmy/MainController">商品検索</a>へ
<br>


</body>
</html>
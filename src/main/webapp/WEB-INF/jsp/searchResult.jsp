<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admincss/style.css">
</head>
<body>
<div class="container">
<h3>キーワード "<c:out value = "${keyword}"/>" カテゴリ  "<c:out value = "${categoryName}"/>"  の検索結果</h3>




<table class ="styled-table">
<thead>
<tr>
<th>商品名</th><th>商品の色</th><th>メーカー名</th><th>価格</th>
</tr>
</thead>

<c:forEach var="item" items="${itemsDto}">
<tr>
<td><a href="/brmy/itemDetailController?itemId=${item.itemId}">${item.name}</a><c:if test ="${item.recommended=='true'}">オススメ!</c:if></td><td>${item.color}</td><td>${item.manufacturer}</td><td>${item.price}円</td> 
</tr>
</c:forEach>

</table>



<ul class="example">
<c:set var="num" value="${pageNumber-10}"></c:set>
<a href="/brmy/MainController2?keyword=${keyword}&categoryId=${categoryId}&pageNumber=${num}"class ="styled-link">
<c:if test="${pageNumber<=0 }"></a></c:if>
前へ
<c:if test="${pageNumber<=0 }"><a></c:if></a>
<a href="/brmy/MainController2?keyword=${keyword}&categoryId=${categoryId}&pageNumber=0"class ="styled-link">
<c:if test="${pageNumber<=0 }"></a></c:if>
1
<c:if test="${pageNumber<=0 }"><a></c:if></a>
<a href="/brmy/MainController2?keyword=${keyword}&categoryId=${categoryId}&pageNumber=10"class ="styled-link">2</a>
<a href="/brmy/MainController2?keyword=${keyword}&categoryId=${categoryId}&pageNumber=20"class ="styled-link">3</a>
<a href="/brmy/MainController2?keyword=${keyword}&categoryId=${categoryId}&pageNumber=30"class ="styled-link">4</a>

<a href="/brmy/MainController2?keyword=${keyword}&categoryId=${categoryId}&pageNumber=40"class ="styled-link">
<c:if test="${pageNumber>=40 }"></a></c:if>
5
<c:if test="${pageNumber>=40 }"><a></c:if></a>
<c:set var="num" value="${pageNumber+10}"></c:set>
<a href="/brmy/MainController2?keyword=${keyword}&categoryId=${categoryId}&pageNumber=${num}"class ="styled-link">
<c:if test="${pageNumber>=40 }"></a></c:if>
次へ
<c:if test="${pageNumber>=40 }"><a></c:if></a>

</ul>





<br>
<a href = "/brmy/MainController"class ="styled-link">商品検索</a>へ
<br>


</div>
</body>
</html>
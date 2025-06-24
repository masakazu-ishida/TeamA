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


      
<c:choose>
    <c:when test="${empty cart }">
        
   <h1>カートの中身がありません</h1>
    </c:when>
    <c:otherwise>
 <h2>以下の商品を購入しますか？</h2>
<table border="1">
<tr>
<th>商品名</th><th>商品の色</th><th>メーカー名</th><th>単価</th><th>数量</th>
</tr>
<c:set var="sum" value="0" />
<c:forEach var="item" items="${cart }">
<tr>
<td>${item.itemsDTO.name }</td><td>${item.itemsDTO.color }</td><td>${item.itemsDTO.manufacturer }</td><td>${item.itemsDTO.price }</td><td>${item.amount }</td>   
</tr>
<c:set var="sum" value="${sum+item.itemsDTO.price*item.amount}" />
</c:forEach>



</table>

</c:otherwise>
</c:choose>
合計${sum }円
<br>
<br>
<form action="/brmy/PurchaseCommitController" method="post">
清算方法
<br>
<select name="payment">
<option selected>代金引換</option>
</select>
<br><br>
配送先<br>
<input type="radio" name="destination" value="registerd" checked>
ご自宅<br>
<input type="radio" name="destination" value="another" >
配送先を指定 <br>
ご住所<br>
<input type="text" name="address">
<br><br>
購入しますか?
<br>
<input type="submit" value="購入する">
</form>
<a href="/brmy/main">商品検索 </a>
へ



</body>
</html>
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
<c:choose>
    <c:when test="${empty cart }">
        
   <h1>カートの中身がありません</h1>
    </c:when>
    <c:otherwise>
        <h2>ショッピングカート内の商品一覧</h2>
<table border="1">
<tr>
<th>商品名</th><th>商品の色</th><th>メーカー名</th><th>単価</th><th>数量</th><th></th>
</tr>
<c:set var="sum" value="0" />
<c:forEach var="item" items="${cart }">
<tr>
<td>${item.itemsDTO.name }</td><td>${item.itemsDTO.color }</td><td>${item.itemsDTO.manufacturer }</td><td>${item.itemsDTO.price }</td><td>${item.amount }</td><td><a href="/brmy/RemoveFromCartConfirm?itemId=${item.itemId }">削除</a></td>    
</tr>
<c:set var="sum" value="${sum+item.itemsDTO.price*item.amount}" />
</c:forEach>



</table>

合計${sum }円

<form action="/brmy/PurchaseConfirmController" method="post">
<input type="submit" value="購入する">
</form>

</c:otherwise>
</c:choose>


<br>
<a href="/brmy/main">商品検索 </a>
へ
</body>
</html>
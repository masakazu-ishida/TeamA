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
    <c:when test="${flg=='flg'}">
        
   <h1>在庫がありません</h1>
    </c:when>
    <c:otherwise>
     <h1>購入商品確認画面</h1>
<h2>以下の商品を購入しました。</h2>

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

<br>
合計${sum }円
<br>
清算方法${paymen }
<br>
配送先${juusyo}


</c:otherwise>
</c:choose>







<br>
<a href="/brmy/MainController">商品検索 </a>
へ




</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>ショッピングカート</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
        <h1>ショッピングカート内の商品一覧</h1>
        <br/>
<table border="1">
    <tr style="background-color: rgb(128, 128, 255); color: white;">
    <th>商品名</th>
    <th>商品の色</th>
    <th>メーカー名</th>
    <th>単価</th>
    <th>数量</th>
    <th> </th>
</tr>

    <c:forEach items="${cartList}" var="cart">
        <tr>
            <td><c:out value="${cart.item.itemName}" /></td>
            <td><c:out value="${cart.item.color}" /></td>
            <td><c:out value="${cart.item.manufacturer}" /></td>
            <td><fmt:formatNumber value="${cart.item.price}" />円</td>
            <td><c:out value="${cart.amount}" />個</td>
            <td>
            <a href="${pageContext.request.contextPath}/cartDeleteConfirmation?itemId=${cart.item.itemId}">削除
            </a></td>
            </tr>
            </c:forEach></table>
            <p>合計　<fmt:formatNumber value="${grandTotal}" />円</p>
            
   

<br>

<input type="button" value="購入する" onclick = "location.href = '${pageContext.request.contextPath}/ItemsInCartController'">

<br>
<a href="${pageContext.request.contextPath}/main">商品検索へ戻る</a>

</body>
</html>
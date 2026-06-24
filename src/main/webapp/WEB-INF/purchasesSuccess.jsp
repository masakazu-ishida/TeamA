<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>商品の購入完了</title>
    <link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/style.css' />
</head>
<body>
    <h3>以下の商品の購入を完了しました。</h3>
    <br />
    
    <table>
        <tr>
            <th>商品名</th>
            <th>商品の色</th>
            <th>メーカー名</th>
            <th>単価</th>
            <th>数量</th>
        </tr>
        
        <c:forEach items="${cartList}" var="cart">
            <tr>
                <td><c:out value="${cart.item.itemName}" /></td>
                <td><c:out value="${cart.item.color}" /></td>
                <td><c:out value="${cart.item.manufacturer}" /></td>
                <td><fmt:formatNumber value="${cart.item.price}" />円</td>
                <td><c:out value="${cart.amount}" /> 個</td>
            </tr>
            <c:set var="grandTotal" value="${grandTotal + (cart.item.price * cart.amount)}" />
        </c:forEach>
    </table>
    
    合計 <fmt:formatNumber value="${grandTotal}" /> 円<br /><br />
<div class="summary-card">
   <p><span>清算方法</span><c:out value="${paymentMethod}" /><br /><br />
    <p>
    <span>配送先</span>
    <c:choose>
        <c:when test="${destination == 'registered'}">
            ご自宅
        </c:when>
        <c:otherwise>
            ご指定先（<c:out value="${address}" />）
        </c:otherwise>
    </c:choose>
    </p>
    <br /><br />
    </div>
    
    <a href='${pageContext.request.contextPath}/main'>商品検索へ</a><br />
</body>

</html>
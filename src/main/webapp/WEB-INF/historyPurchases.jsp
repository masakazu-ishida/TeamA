<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴一覧</title>
</head>
<body>
<h3>購入履歴の一覧</h3>
<br />
<table border="1">
    <tr>
        <th>注文日</th>
        <th>購入商品</th>
        <th>配送先</th>
        <th></th>
    </tr>
    
    <c:forEach items="${List}" var="history">
    <tr>
        <td><c:out value="${history.purchasedDate}" /></td>
        
        <td>
            <table border="1">
                <t>
                    <th>商品名</th>
                    <th>色</th>
                    <th>メーカー</th>
                    <th>単価</th>
                    <th>数量</th>
                </tr>
                <c:forEach items="${history.detailsList}" var="detail">
                <tr>
                    <td><c:out value="${detail.itemDTO.itemName}" /></td>
                    <td><c:out value="${detail.itemDTO.color}" /></td>
                    <td><c:out value="${detail.itemDTO.manufacturer}" /></td>
                    <td><fmt:formatNumber value="${detail.itemDTO.price}" />円</td>
                    <td><c:out value="${detail.amount}" />個</td>
                </tr>
                </c:forEach>
            </table>
        </td>
        
        <td><c:out value="${history.destination == null ? '自宅' : history.destination}" /></td>
        <td><c:if test="${history.cancel == false}"><a href="${pageContext.request.contextPath}/cancelConfirmation?purchaseId=${history.purchaseId}">キャンセル</a></c:if>
        <c:if test="${history.cancel == true}">　　　</c:if>
        </td>
        
    </tr>
    </c:forEach>
</table>
<br /><br />
<a href='${pageContext.request.contextPath}/main'>商品検索へ戻る</a>
</body>
</html>
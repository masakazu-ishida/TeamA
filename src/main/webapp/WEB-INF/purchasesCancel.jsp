<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %> 
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>注文のキャンセル完了</title>
 <link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body class="cancel-complete-page">
<h3>以下の注文をキャンセルしました。</h3>
		<br />
		<table border="1">
    <tr>
        <th>注文日</th>
        <td><c:out value="${result.purchasedDate}" /></td>
    </tr>
    <tr>
        <th>購入商品</th>
        <td>
            <table border="1">
                <tr>
                    <th>商品名</th>
                    <th>色</th>
                    <th>メーカー</th>
                    <th>単価</th>
                    <th>数量</th>
                </tr>
                <c:forEach items="${result.detailsList}" var="detail">
                <tr>
                    <td><c:out value="${detail.itemDTO.itemName}" /></td>
                    <td><c:out value="${detail.itemDTO.color}" /></td>
                    <td><c:out value="${detail.itemDTO.manufacturer}" /></td>
                    <td><fmt:formatNumber  value="${detail.itemDTO.price}" />円</td>
                    <td><c:out value="${detail.amount}" />個</td>
                </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <th>配送先</th>
        <td>
            <c:out value="${result.destination == null ? '自宅' : result.destination}" />
        </td>
    </tr>
</table>
<a href='${pageContext.request.contextPath}/main'>商品検索へ</a><br />

</body>

</html>
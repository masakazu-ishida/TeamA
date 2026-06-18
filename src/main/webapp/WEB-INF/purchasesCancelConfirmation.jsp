<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>以下の注文をキャンセルしてよろしいですか？</h3>
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
                    <td><c:out value="${detail.itemDTO.price}" />円</td>
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

<form action="${pageContext.request.contextPath}/executeCancel" method="post">
    <input type="hidden" name="purchaseId" value="${result.purchaseId}">
    <input type="submit" value="キャンセル">
</form>
</body>
</html>
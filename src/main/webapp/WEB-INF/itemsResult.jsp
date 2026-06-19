<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>検索結果</title>
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body class="result-page">
<h3>キーワード "<c:out value="${name}" />" カテゴリ "<c:if test="${categoryId == 0}">すべて</c:if>
													<c:if test="${categoryId == 1}">帽子</c:if>
													<c:if test="${categoryId == 2}">鞄</c:if>" の検索結果</h3>
<br />
<table border="1">
    <tr style="background-color: rgb(128, 128, 255); color: white;">
    <th>商品名</th>
    <th>商品の色</th>
    <th>メーカー名</th>
    <th>価格</th>
</tr>

    
    <c:forEach items="${itemsList}" var="item">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/itemsDetail?itemId=${item.itemId}">
    <c:out value="${item.itemName}" />
    
    <c:if test="${item.recommended == true}">
        <span style="color: red; font-size: 0.8rem; font-weight: bold; margin-left: 5px;">オススメ！</span>
    </c:if>
</a>
            </td>
            <td><c:out value="${item.color}" /></td>
            <td><c:out value="${item.manufacturer}" /></td>
            <td><c:out value="${item.price}" />円</td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="${pageContext.request.contextPath}/main">商品検索へ戻る</a>
</body>
</html>

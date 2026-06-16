<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>
<h3>キーワード "<c:out value="${param.keyword}" />" カテゴリ "<c:out value="${param.cnumber}" />" の検索結果</h3>
<br />
<table border="1"> <!-- borderをつけると枠線が見やすくなります -->
    <tr>
        <th>商品名</th>
        <th>商品の色</th>
        <th>メーカー名</th>
        <th>価格</th>
    </tr>
    
    <c:forEach items="${itemsList}" var="item">
        <tr>
            <!-- リンクの先頭の / を無くし、ゲッターの書き方をシンプルにしました -->
            <td>
                <a href="itemsDetail?itemId=${item.itemId}">
                    <c:out value="${item.itemName}" />
                </a>
            </td>
            <td><c:out value="${item.color}" /></td>
            <td><c:out value="${item.manufacturer}" /></td>
            <td><c:out value="${item.price}" />円</td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="main.jsp">商品検索へ戻る</a>
</body>
</html>

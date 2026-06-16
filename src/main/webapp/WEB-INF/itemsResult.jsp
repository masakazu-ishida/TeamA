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
<h3>キーワード "<c:out value="${name}" />" カテゴリ "<c:out value="${categoryId}" />" の検索結果</h3>
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
                <a href="/TeamA/jp.co.cuatro/servlet/itemsDetail?itemId=${item.itemId}">
                    <c:out value="${item.itemName}" />
                    <c:if test="${item.recommended == true}">
  					<h6 style="color: red;">オススメ！</h6>
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
<a href="main.jsp">商品検索へ戻る</a>
</body>
</html>

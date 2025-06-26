<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admincss/style.css">
</head>
<body >
<div class="container">
<h1>商品の検索</h1>


<br><form action = "/brmy/MainController" method = "post">
キーワード<br><input type = "text" name = "keyword"><br>


<input type = "hidden" value = "0" name = "pageNumber">



カテゴリ<br><select name = "categoryId">
<option selected="" value="0">すべて</option>
<option value="1">帽子</option>
<option value="2">鞄</option>
</select><br>
<input type = "submit" value = "検索"><br>

</form>
<c:choose>
    <c:when test="${empty user }">
        <br><a href="/brmy/login">ログイン</a>
    </c:when>
    <c:otherwise>
        <br><a href="/brmy/CartDisplayController"class="styled-link">ショッピングカートを見る</a>
</c:otherwise>
</c:choose>


</div>
</body>
</html>
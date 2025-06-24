<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h3>商品の検索を行います。</h3>


<br><form action = "/brmy/MainController" method = "post">
キーワード<br><input type = "text" name = "keyword"><br>


<input type = "hidden" value = "0" name = "pageNumber">



カテゴリ<br><select name = "categoryId">
<option selected="" value="0">すべて</option>
<option value="1">帽子</option>
<option value="2">鞄</option>
</select><br>
<input type = "submit" value = "検索"><br>

<br><a href="">ショッピングカートを見る</a>

</form>


</body>
</html>
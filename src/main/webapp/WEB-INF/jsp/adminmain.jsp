<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admincss/style.css">
</head>
<body>
<div class="container">
<h1>管理者メインページ</h1>

<c:if test="${not empty errorMsg}">
	<p>${errorMsg}</p>
</c:if>


<table border="1">
	<form action="/brmy/PurchaseSearchController" method="post">
<tr>
	<th>会員ID</th><td><input type ="text" name="userId"></td>
</tr>
<tr>	
	<td colspan="2"><input type="submit" value="検索"></td>
</tr>
	</form>
</table>
</div>
</body>
</html>
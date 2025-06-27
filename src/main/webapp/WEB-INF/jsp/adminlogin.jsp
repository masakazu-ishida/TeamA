<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admincss/style.css">
</head>
<body>
<div class="container">
<h1>管理者ログイン</h1>
<c:if test="${not empty errorMsg}">
	<p>${errorMsg}</p>
</c:if>
<table class ="info-table">

<tr>
	<form action="/brmy/AdminLoginController" method="post">
	<th>管理者ID</th> <td><input type ="text" name="userid"></td> 
</tr>
<tr>
	<th>パスワード</th>	<td><input type = "password" name="password"></td>
</tr>
<tr>	
		<td colspan="2"><input type="submit" value="ログイン"></td>
</tr>
	</form>
</table>
</div>
</body>
</html>
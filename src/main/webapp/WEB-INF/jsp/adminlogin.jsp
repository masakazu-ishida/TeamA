<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>管理者ログイン</h1>
<form action="/TeamA/AdminLoginController" method="post">
管理者ID<input type ="text" name="userid">
パスワード<input type = "text" name="password">
<input type="submit" value="ログイン">
</form>

</body>
</html>
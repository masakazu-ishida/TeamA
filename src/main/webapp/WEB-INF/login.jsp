<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>会員ログイン</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3>ログインしてください。</h3>
		<br />
		<form action="${pageContext.request.contextPath}/login" method="POST">
			<table>
				<tr>
					<th>会員ID</th>
					<td><input type='text' class='id' name='id' /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' class='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='ログイン' /></td>
				</tr>
			</table>
		</form>
	</body>
</html>
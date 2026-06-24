<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>会員情報変更</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body class="member-info-page">
	<h2>会員情報の変更画面</h2>
	<br />
	<h3>会員情報</h3>
		<br />
		<form action='' method='POST'>
			<table>
				<tr>
					<th>会員ID</th>
					<td>someone@example.com</td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><input type='text' name='name' value='' class='text' /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' name='password1' class='password' /></td>
				</tr>
				<tr>
					<th>パスワード(確認)</th>
					<td><input type='password' name='password2' class='password' /></td>
				</tr>
				<tr>
					<th>ご住所</th>
					<td><input type='text' name='address' value='' class='text' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='変更' /></td>
				</tr>
			</table>
		</form>
		<a href=''>退会する</a><br />
	<br />
	<a href='${pageContext.request.contextPath}/history'>購入履歴を表示</a>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
		<h3>ログインしてください。</h3><br />
		<c:out value="${error}"/>
		<br />
		<form action='/brmy/login' method='POST'>
			<table>
				<tr>
					<th>会員ID</th>
					<td><input type='text' name='userId' /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='ログイン' /></td>
				</tr>
			</table>

		<input type="hidden" name="source" value="${source}">
		<input type="hidden" name="itemId" value="${itemId}">
		<input type="hidden" name="amount" value="${amount}">

			
		</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>商品検索</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<%-- ログイン済の場合（セッションのloginUserが存在するとき） --%>
		<c:if test="${not empty sessionScope.loginUser}">
			<h2>ようこそ！${sessionScope.loginUser.name}さん</h2>
		</c:if>
		
		<h3>商品の検索を行います。</h3>
		<br />
		<form action='${pageContext.request.contextPath}/ItemsSerch' method='GET'>
			キーワード<br />
			<input type='text' name="name" /><br />
			カテゴリ<br />
			<select name="categoryId">
				<option selected value='0'>すべて</option>
				<option value='1'>帽子</option>
				<option value='2'>鞄</option>
			</select><br/>
			<input type='submit' value='検索' /><br/>
		</form>
		<a href='${pageContext.request.contextPath}/cartDisplay'>ショッピングカートを見る</a><br/><br/>
		<c:choose>
			<%-- 未ログインの場合（セッションのloginUserが空のとき） --%>
			<c:when test="${empty sessionScope.loginUser}">
				<a href='${pageContext.request.contextPath}/login'>ログイン</a><br /><br />
			</c:when>

			<%-- ログイン済の場合（セッションのloginUserが存在するとき） --%>
			<c:otherwise>
				<a href='${pageContext.request.contextPath}/historyCart'>購入履歴を表示</a><br />
			</c:otherwise>
		</c:choose>
	</body>
</html>
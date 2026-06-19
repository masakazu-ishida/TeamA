<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>ショッピングカート内の商品を削除</title>
 <link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>以下の商品をショッピングカートから削除してよろしいですか？</h3>
	<br />
		<c:out value="${confirmCart.item.itemName}" /><br />
		<c:out value="${confirmCart.item.manufacturer}" /><br />
		<fmt:formatNumber value="${confirmCart.item.price}" />円<br />
		数量<c:out value="${confirmCart.amount}" />個<br />
		
		<form action='${pageContext.request.contextPath}/cartDelete' method='POST'>
			<input type='hidden' name='itemId' value= "${confirmCart.item.itemId}" />
			<input type='submit' value='削除する' /><br />
		</form>
	    <a href="${pageContext.request.contextPath}/main">商品検索</a>へ<br />	

</body>
</html>
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
<body class="cart-deleted-page">

	<h3>以下の商品をショッピングカートから削除しました。</h3>
	<br />
		<c:out value="${searchCart.item.itemName}" /><br />
		<c:out value="${searchCart.item.manufacturer}" /><br />
		<fmt:formatNumber value="${searchCart.item.price}" />円<br />
		数量<c:out value="${searchCart.amount}" />個<br />
		
	    <a href="${pageContext.request.contextPath}/main">商品検索へ</a><br />	

</body>
</html>
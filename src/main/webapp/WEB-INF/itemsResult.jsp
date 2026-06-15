<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>キーワード "<%= request.getParameter("keyword") %>" カテゴリ "<%= request.getParameter("cnumber") %>" の検索結果</h3>
		<br />
		<table border="1">
			<tr>
				<th>商品名</th>
				<th>商品の色</th>
				<th>メーカー名</th>
				<th>価格</th>
			</tr>
			<form action="itemsDetailResult.jsp" method="get">
			
			<c:forEach items="${itemsList}" var="item">
       			<tr>
            		<td><a href='/itemsDetail?itemId=${item.getItemId()}'><c:out value="${item.getItemName()}" /></a></td>
            		<td><c:out value="${item.getColor()}" /></a></td>
            		<td><c:out value="${item.getManufacturer()}" /></a></td>
            		<td><c:out value="${item.getPrice()}" /></a></td>
        		</tr>
    		</c:forEach>
			</form>
			
		</table>
<br><a href='main.jsp'>商品検索</a>へ
</body>
</html>
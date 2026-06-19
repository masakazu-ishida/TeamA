<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<title>Insert title here</title>
	<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body class="detail-page">
<h3>商品の詳細表示</h3>
		<br />
		<table border="1">
        <tr>
				<th>商品名</th>
				<td>${itemsDetail.itemName}</td>
			</tr>
			<tr>
				<th>商品の色</th>
				<td>${itemsDetail.color}</td>
			</tr>
			<tr>
				<th>メーカー名</th>
				<td>${itemsDetail.manufacturer}</td>
			</tr>
			<tr>
				<th>価格</th>
				<td>${itemsDetail.price}</td>
			</tr>
			<tr>
				<th>在庫数</th>
				<td>${itemsDetail.stock}</td>
			</tr>
		</table>
		<form action='${pageContext.request.contextPath}/cartAdd' method='POST'>
			数量
			<select name='amount'>
				<option selected value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>
				<option value='5'>5</option>
			</select><br />
			<input type='hidden' name='itemId' value='${itemsDetail.itemId}' />
			<input type='submit' value='ショッピングカートに入れる' /><br />
		</form>
		<a href='${pageContext.request.contextPath}/main'>商品検索へ</a><br />

</body>
</html>
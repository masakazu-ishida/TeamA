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
<h3>商品の詳細表示</h3>
		<br />
		<table>
        <tr>
				<th>商品名</th>
				<td>${itemsDetailList.itemName}</td>
			</tr>
			<tr>
				<th>商品の色</th>
				<td>${itemsDetailList.color}</td>
			</tr>
			<tr>
				<th>メーカー名</th>
				<td>${itemsDetailList.manufacturer}</td>
			</tr>
			<tr>
				<th>価格</th>
				<td>${itemsDetailList.price}</td>
			</tr>
			<tr>
				<th>在庫数</th>
				<td>${itemsDetailList.stock}</td>
			</tr>
		</table>
		<form action='/TeamA/jp.co.cuatro/servlet/cartAll' method='POST'>
			数量
			<select name='amount'>
				<option selected value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>
				<option value='5'>5</option>
			</select><br />
			<input type='hidden' name='itemId' value='${itemsDetailList.itemId}' />
			<input type='submit' value='ショッピングカートに入れる' /><br />
		</form>
		<a href='main.jsp'>商品検索</a>へ<br />

</body>
</html>
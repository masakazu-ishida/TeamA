<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品の購入確認</title>
	<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h1>以下の商品を購入しますか？</h1><br/>
	<table>
		<tr>
            <th>商品名</th>
            <th>商品の色</th>
            <th>メーカー名</th>
            <th>単価</th>
            <th>数量</th>
        </tr>
      
<c:forEach items="${cartList}" var="cart">
        <tr>
            <td><c:out value="${cart.item.itemName}" /></td>
            <td><c:out value="${cart.item.color}" /></td>
            <td><c:out value="${cart.item.manufacturer}" /></td>
            <td><c:out value="${cart.item.price}" />円</td>
            <td><c:out value="${cart.amount}" />個</td>
        </tr>
        <c:set var="grandTota" value="${grandTota + cart.total}" />
</c:forEach> </table> 
 	<p>合計<c:out value="${grandTota}" />円</p>
    <form action="${pageContext.request.contextPath}/PurchasesCompletionController" method="post">
        
        <p>清算方法</p>
        <select name="paymentMethod">
            <option value="代金引換">代金引換</option>
        </select><br/>

        <p>配送先</p>
        <label>
            <input type="radio" name="deliveryLocation" value="ご自宅" checked> ご自宅
        </label><br/>
        <label>
            <input type="radio" name="deliveryLocation" value="配送先を指定"> 配送先を指定
        </label><br/>
        <p>ご住所</p>
        <input type="text" name="address" class="text"><br/>

        <p>購入しますか？</p>
        <input type="submit" value="購入する">

    </form><br/>
    <a href="${pageContext.request.contextPath}/main">商品検索へ</a>
</body>
</html>
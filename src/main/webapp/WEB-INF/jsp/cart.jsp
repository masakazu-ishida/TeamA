<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${empty list }">

<h2>ショッピングカート内の商品一覧</h2>
<table>
<tr>
<th>商品名</th><th>商品の色</th><th>メーカー名</th><th>単価</th><th>数量</th>
</tr>






</table>
</c:if>



</body>
</html>
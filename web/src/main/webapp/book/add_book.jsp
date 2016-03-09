<%--
  Created by IntelliJ IDEA.
  User: adon
  Date: 2016/1/31 0031
  Time: 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Add Book</title>
</head>
<body>
<h2>Add Book</h2>

<form method="post" action="/book/book.do?method=add">
	id:<input type="text" name="id">
	bookname:<input type="text" name="name">
	author:<input type="text" name="author">
	<input type="submit" value="Add">
</form>

<form method="post" action="/book/book.do?method=update">
	id:<input type="text" name="id">
	bookname:<input type="text" name="name">
	author:<input type="text" name="author">
	<input type="submit" value="Update">
</form>

<form method="post" action="/book/book.do?method=saveOrUpdate">
	id:<input type="text" name="id">
	bookname:<input type="text" name="name">
	author:<input type="text" name="author">
	<input type="submit" value="saveOrUpdate">
</form>

<form method="post" action="/book/book.do?method=get">
	id:<input type="text" name="id">
	<input type="submit" value="Get">
</form>
</body>
</html>

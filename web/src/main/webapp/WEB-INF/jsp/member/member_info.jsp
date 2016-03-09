<%--
  Created by IntelliJ IDEA.
  User: adon
  Date: 2016/3/6 0006
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>memberInfo</title>
</head>
<body>
<c:forEach items="${data}" var="m">
	${m}<br>
</c:forEach>
</body>
</html>

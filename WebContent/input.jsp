<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add grade</title>
</head>
<body>
	<form action="Gradebook" method="post">
		<input type="hidden" name="action" value="add"> Assignment ID:
		<input type="text" name="aid"><br> Assignment Type: <input
			type="text" name="atype"><br> Grade: <input type="text"
			name="grade"><br> <input type="submit" value="Add grade">
	</form>
	<c:if test="${loggedin}">
		<a href="choice.jsp">Return</a>
	</c:if>
	<c:if test="${loggedin == false}">
		<a href="index.html">Return to login screen</a>
	</c:if>
</body>
</html>
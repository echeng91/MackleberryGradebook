<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Successful!</title>
</head>
<body>
<h4>Welcome, <c:out value="${studentuser.firstname}"/></h4>
	<form action="input.jsp" method="post">
		<input type="submit" value="Add a grade">
	</form>
	<form action="update.jsp" method="post">
		<input type="submit" value="Update a grade">
	</form>
	<form action="Gradebook" method="post">
		<input type="hidden" name="action" value="view">
		<input type="submit" value="View your grades">
	</form>
</body>
</html>
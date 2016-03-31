<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View grades</title>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<c:if test="${loggedin}">
		<h4>
			<c:out value="${studentuser.firstname} ${studentuser.lastname}" />
			's grades
		</h4>
	</c:if>
	<c:if test="${loggedin == false}">
		<h4>All grades</h4>
	</c:if>
	<table>
		<tr>
			<th>Assignment ID</th>
			<th>Assignment Type</th>
			<th>Grade</th>
		</tr>
		<c:forEach items="${assignments}" var="asnmnt">
			<tr>
				<td><c:out value="${asnmnt.AId}" /></td>
				<td><c:out value="${asnmnt.AType}" /></td>
				<td><c:out value="${asnmnt.grade}" /></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${loggedin}">
		<a href="choice.jsp">Return</a>
	</c:if>
	<c:if test="${loggedin == false}">
		<a href="index.html">Return to login screen</a>
	</c:if>
</body>
</html>
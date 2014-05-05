<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
	<c:url value="/resources/text.txt" var="url"/>
	<spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl" />
	Spring URL: ${springUrl} at ${time}
	<br>
	JSTL URL: ${url}
	<br>
	Message: ${message}
	
	<c:choose>
		<c:when test="${not empty users}"> 
			<c:forEach var="user" items="${users}">
		    	<br>
		    	Id : ${user.id}
		    	<br>
				First name: ${user.firstName}
				<br>
				Last name: ${user.lastName}
				<br>
				<a href="/user/delete/${user.id}">Delete this user</a>
			</c:forEach>
		</c:when>
		<c:otherwise>
			There are no users yet.
		</c:otherwise>
	</c:choose>
	
	<br>
	<a href="/user/add">Add user</a>
	
</body>

</html>

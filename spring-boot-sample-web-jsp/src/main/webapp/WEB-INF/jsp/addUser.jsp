<!DOCTYPE html>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
	<form:form method="POST" action="/user/add">
		First Name: 
		<form:input path="firstName" />
		<br />
		Last Name: 
		<form:input path="lastName" />
		<br>
		<input type="submit" value="Submit" />
	</form:form>
	
</body>

</html>

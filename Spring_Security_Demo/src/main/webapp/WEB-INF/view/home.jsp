<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Home Page</title>
</head>

<body>
    <h2>Home Page</h2>
    <hr>

	<div>
		Welcome to the home page!
	</div>
	<hr>
	
	<div>
		User: <security:authentication property="principal.username" />
		<br><br>
		Role(s): <security:authentication property="principal.authorities" />
	</div>
    <hr>
    
    <div>
    	<security:authorize access="hasRole('MANAGER')"> 
    		<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
    	</security:authorize>
    	
    	<security:authorize access="hasRole('ADMIN')">   		
    		<a href="${pageContext.request.contextPath}/systems">IT System Meeting</a>
    	</security:authorize>
    </div>
    
    <hr>
    
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<button type="submit" value="Logout">Log Out</button>
    </form:form>
    
</body>

</html>
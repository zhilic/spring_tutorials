<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Access Denied</title>
</head>

<body>
    <h2>Access Denied</h2>
    <hr>

	<div>
		Oops, you don't have the authorities to visit this page.
	</div>
	<hr>
	
	<a href="${pageContext.request.contextPath}/">Back to Home Page</a>
    
</body>

</html>
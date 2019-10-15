<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>Custom Login Page</title>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>

<div class="row justify-content-center">
	<div id="loginbox" style="margin-top: 50px;" class="col-11 col-sm-6 col-md-4 col-xl-3">
	
		<div class="card" style="border-color: #C2E5FB">
			<div class="card-header" style="background-color: #C2E5FB; color: #1575B2">Sign In</div>
			<div class="card-body">
			
				<!-- Best practice is to use the Spring MVC Form tag -->
				<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
					
					<div class="form-group">
						<div class="row">
							<!-- Check for login error -->
							<c:if test="${param.error != null}">
								<div class="alert alert-danger offset-1 col-10">Invalid username and password!</div>
							</c:if>
							
							<!-- Check for logout -->
							<c:if test="${param.logout != null}">
								<div class="alert alert-success offset-1 col-10">You've been logged out.</div>
							</c:if>
						</div>
						
						<div class="input-group mt-5">
							<div class="input-group-prepend">
								<div class="input-group-text"><i class="fas fa-user"></i></div>
							</div>
							<input class="form-control" type="text" name="username" placeholder="username">
						</div>
						<div class="input-group mt-4">
							<div class="input-group-prepend">
								<div class="input-group-text"><i class="fas fa-lock"></i></div>
							</div>
							<input class="form-control" type="password" name="password" placeholder="password">
						</div>
						
						<button type="submit" class="btn btn-success my-4" value="Login">Login</button>
											
					</div>
					
					<!-- If tag form:form is not used, need to manually add CSRF tokens -->
					<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> -->					
				</form:form>
					
			</div>
		</div>
	
	</div>
</div>	


</body>
</html>
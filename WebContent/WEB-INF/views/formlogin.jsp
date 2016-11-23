<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fala Cidadão - Login</title>
	<c:import url="config.jsp"/>
	<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>

</head>

<body>
	<c:import url="cabecalho.jsp" />
	<c:import url="banner.jsp" />
	<div class="container login pdt">
		<div class="panel panel-default">
			<div class="panel-body">
				<form action="${path}/efetuaLogin" method="post">
					<div class="panel-heading">Fala Cidadão - Login</div>

					<div class="form-group">
						<label for="login">Login</label> <input id="login" name="login"
							size=50 class="form-control" required>
					</div>
					<div class="form-group">

						<label for="senha">Senha</label> <input type="password" id="senha"
							name="senha" class="form-control" required>
					</div>
					<div class="form-group">
						<button id="btn-login" class="btn btn-success">Entrar</button>
					</div>



				</form>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp" />
</body>
</html>
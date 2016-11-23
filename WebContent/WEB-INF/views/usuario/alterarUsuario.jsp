<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<c:import url="../config.jsp"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>

<meta charset="UTF-8">
<title>Alterar Dados</title>
</head>
<body>

	<c:import url="../cabecalho.jsp" />
	<div class="container">
	<c:if test="${not empty usuarioLogado}">
		<form class="form-horizontal" method="POST" action="alterardados">
			<div class="form-group">
				<label class="control-label col-sm-2" for="nome"> Nome</label>
				<div class="col-sm-10">
					<input type="text" id="nome" name="nome" value="${usuariodados.nome}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email"> Email</label>
				<div class="col-sm-10">
					<input type="text" id="email" name="email" value="${usuariodados.email}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="login"> Login </label>
				<div class="col-sm-10">
					<input type="text" id="login" name="login" value="${usuariodados.login}">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="celular"> Celular</label>
				<div class="col-sm-10">
					<input type="text" id="celular" name="celular" value="${usuariodados.celular}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
					<a href="menu"><button type="button" onClick="menu" class="btn btn-default">Voltar</button></a>
				</div>
			</div>
		</form>
	</c:if>
	</div>
</body>
</html>


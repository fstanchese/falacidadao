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
	<div class="container pdt">
		<div class="row">
			<div class="col-sm-6">
				<c:if test="${not empty usuarioLogado}">
					<form class="form-horizontal" method="POST" action="alterardados">
						<span>Nome</span>
						<div class="form-group">
							<input type="text" required placeholder="Digite seu nome completo" class="form-control" id="nome" name="nome" value="${usuariodados.nome}">
						</div>
						<div class="form-group">
							<span>Email</span>
							<input type="email" required class="form-control" placeholder="Informe um email de cadastro" id="email" name="email" value="${usuariodados.email}">
						</div>
						<div class="form-group">
							<span>Login</span>		
							<input type="text" required class="form-control" placeholder="Escolha um login de acesso" id="login" name="login" value="${usuariodados.login}">
						</div>
						<div class="form-group">
							<span>Senha</span>
							<input type="password" required class="form-control" placeholder="Senha de acesso" id="login" name="senha" value="${usuariodados.senha}">
						</div>
						<div class="form-group">
							<span>Celular</span>
							<input type="text" required placeholder="Telefone de contato (11) 9999-9999" class="form-control" id="celular" name="celular" value="${usuariodados.celular}">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-success">Alterar <i class="glyphicon glyphicon-ok"></i></button>
							<a href="menu"><button type="button" onClick="menu" class="btn btn-default">Voltar</button></a>
						</div>
					</form>
				</c:if>
			</div>
			<div class="col-sm-6">
				<img class="img-responsive" style="margin:35px auto" src="${path}/resources/imagens/edita.jpg" alt="Editar seus dados">				
			</div>
		</div>
	</div>
<c:import url="../footer.jsp" />	
</body>
</html>


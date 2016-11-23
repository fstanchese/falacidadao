<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../config.jsp"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>

<meta charset="UTF-8">
<title>Cadastrar Dados</title>
</head>
<body>
	<c:import url="../cabecalho.jsp" />
	<div class="container pdt">
		<div class="row">
			<div class="col-sm-6">
				<p class="alert alert-info">
					Cadastre-se e faça suas reclamações, sugestões e ajude a melhorar sua cidade!
				</p>
				<form action="finalizarcadastro" method="POST" action="finalizarcadastro">
					<span>Nome</span>
					<div class="form-group">
						<input required type="text" placeholder="Digite seu nome completo" class="form-control" id="nome" name="nome" value="${usuariodados.nome}">
					</div>
					<div class="form-group">
						<span>Email</span>
						<input required type="email" class="form-control" placeholder="Informe um email de cadastro" id="email" name="email" value="${usuariodados.email}">
					</div>
					<div class="form-group">
						<span>Login</span>		
						<input required type="text" class="form-control" placeholder="Escolha um login de acesso" id="login" name="login" value="${usuariodados.login}">
					</div>
					<div class="form-group">
						<span>Senha</span>
						<input required type="password" class="form-control" placeholder="Senha de acesso" id="login" name="senha" value="${usuariodados.senha}">
					</div>
					<div class="form-group">
						<span>Celular</span>
						<input type="text" required placeholder="Telefone de contato (11) 9999-9999" class="form-control" id="celular" name="celular" value="${usuariodados.celular}">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-success">Cadastrar <i class="glyphicon glyphicon-ok"></i></button>
						<a href="menu"><button type="button" onClick="menu" class="btn btn-default">Voltar</button></a>
					</div>
				</form>
				
			</div>
			<div class="col-sm-6">
				<img class="img-responsive" style="margin:35px auto" src="${path}/resources/imagens/cadastro.jpg" alt="Faça seu cadastro em nosso site">				
			</div>
		</div>
	</div>
<c:import url="../footer.jsp" />
</body>
</html>


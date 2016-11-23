<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<c:import url="config.jsp"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>

<meta charset="UTF-8">
<title>Menu Principal</title>
</head>
<body>
<c:import url="cabecalho.jsp" />
<c:import url="banner.jsp" />
<div class="container">
	<div class="row">
		<div class="col-xs-12 text-center" style="padding-top:20px;padding-bottom:20px">
			<p>Bem vindo ao Fala Cidadão.</p>
			<p>O sistema online foi desenvolvido com a finalidade de aprimorar e melhorar ainda mais o atendimento ao cidadão, com recursos avançados, oferecendo agilidade e segurança no registro das solicitações.
			</p>
			<c:if test="${empty usuarioLogado}">
				<p>Para acessar o novo sistema, será necessário realizar um cadastro no primeiro acesso, para isto clique em <strong>Cadastre-se</strong> no menu acima.</p>

				<p>Se já possuir um cadastro, para enviar suas solicitações você deve efetuar o login no sistema, clicando em <strong>Entrar</strong>.</p>
			</c:if>
		</div>
	</div>
</div>
<c:import url="listaMural.jsp" />
<c:import url="footer.jsp" />
    
</body>
</html>


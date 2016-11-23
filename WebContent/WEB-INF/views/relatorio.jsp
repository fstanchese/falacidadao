<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<c:import url="config.jsp"/>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<meta charset="UTF-8">
<title>Alterar Dados</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	<c:if test="${not empty usuarioLogado}">
	<div class="container pdt relatorios text-center">
		<div class="row">
			<div class="col-xs-12">
				<p class="alert alert-info">
					Relatórios informativos de entrada de dados no sistema
				</p>
			</div>
			<div class="col-sm-4 col-xs-12">
				<p>Consultar ranking geral de participação por cidadão.</p>
				<a href="relatorioranking" class="btn btn-info btn-block">Gerar relatório</a>
			</div>
			<div class="col-sm-4 col-xs-12">
				<p>Consultar ranking de sugestões aprovadas para postagem por categoria</p>
				<a href="relatoriocategoria" class="btn btn-info btn-block">Gerar relatório</a>
			</div>
			<div class="col-sm-4 col-xs-12">
				<p>Ranking de sugestões enviadas, avaliadas e índice de devolutiva por avaliador</p>
				<a href="relatoriodevolutivas" class="btn btn-info btn-block">Gerar relatório</a>
			</div>
		</div>
	</div>
	</c:if>
<c:import url="footer.jsp" />
</body>
</html>


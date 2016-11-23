<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style type="text/css">
<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
@IMPORT url("${path}/resources/css/bootstrap.min.css");

@IMPORT url("${path}/resources/css/bootstrap-theme.min.css");

@IMPORT url("${path}/resources/css/custom.css");

@IMPORT url("${path}/resources/css/style.css");
</style>
<script src="${path}/resources/js/jquery.min.js" type="text/javascript"></script>

<meta charset="UTF-8">
<title>Alterar Dados</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	<c:if test="${not empty usuarioLogado}">

		<form class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-2">Consultar
					ranking geral de participação por cidadão.</label>
				<div class="col-sm-10">
					<a href="relatorioranking"><input type="button" value="Gerar relatorio" /></a>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Consultar ranking de sugestões aprovadas para postagem por categoria.</label>
				<div class="col-sm-10">
					<a href="relatoriocategoria"><input type="button" value="Gerar relatorio" /></a>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2"> Ranking de sugestões enviadas, avaliadas e índice de devolutiva por avaliador.</label>
				<div class="col-sm-10">
					<a href="relatoriodevolutivas"><input type="button" value="Gerar relatorio" /></a>
				</div>
			</div>
		</form>


	</c:if>
	<script src="resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>


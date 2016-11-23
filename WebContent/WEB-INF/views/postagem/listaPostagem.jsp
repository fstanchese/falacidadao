<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>

<html>
<head>
	<c:import url="../config.jsp" />
	<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
<script src="${path}/resources/js/jquery.min.js" 		type="text/javascript"></script>
<meta charset="UTF-8">
<title>Postagens</title>
<style>
    .error {
        color: red; font-weight: bold;
    }
    .top-buffer { margin-top:15px; }
</style>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#areaId').on('change', function() {
			this.form.submit();
	  	});
		$('#status').on('change', function() {
			this.form.submit();
	  	});	
	});
</script>
<c:import url="../cabecalho.jsp" />	
<div class="container pdt">

	
   		<div class="panel panel-default">
   			<div class="panel-heading">
			<c:if test="${usuarioLogado.tipoUsuario eq 'SUPERVISOR' or usuarioLogado.tipoUsuario eq 'CIDADAO'}">
				<a href="${path}/postagens/novo" class="btn btn-info" role="button">Nova Postagem</a>
 			</c:if>
			<form class="form-horizontal">
			<div class="row">
			<br>
			<c:if test="${usuarioLogado.tipoUsuario eq 'SUPERVISOR'}">
			<div class="col-sm-6">
				<label for="areaId">Selecione a Área</label> 
				<select id="areaId" name="area_id" class="js-example-diacritics form-control input-sm pokemon">
					<option value=""></option>
					<c:forEach var="area" items="${areas}">
							<option value="${area.id}"
								${area.id==areaFiltro.id ? 'selected' : ''}>${area.descricao}					
							</option>
					</c:forEach>				
				</select>
   			</div>
 			</c:if>
			<c:if test="${usuarioLogado.tipoUsuario eq 'AVALIADOR' or usuarioLogado.tipoUsuario eq 'SUPERVISOR'}">
  			<div class="col-sm-6">
				<label for="status">Selecione a Situação</label> 
				<select id="status" name="status" class="js-example-diacritics form-control input-sm pokemon">
					<option value=""></option>
					<c:forEach var="status" items="${statuss}">
							<option value="${status.descricao}"
								${status.descricao==statusFiltro ? 'selected' : ''}>${status.descricao}					
							</option>
					</c:forEach>				
				</select>
   			</div>
  			</c:if>
   			</div>
			</form>
   			</div>
			<c:if test="${not empty postagens}">
			<table class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th>Protocolo</th>
						<th>Descrição</th>
						<th>Data</th>
						<th>Situação</th>
						<th>Área</th>
						<th width="12%">Acao</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="postagem" items="${postagens}">
						<tr id="row${postagem.id}">
							<td>&nbsp;${postagem.id}</td>
							<td>&nbsp;${fn:substring(postagem.descricao,0,80)}...</td>
							<td><fmt:formatDate value="${postagem.dataSugestao}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
							<td>&nbsp;${postagem.status}</td>
							<td>&nbsp;${postagem.area.descricao}</td>
							<c:if test="${usuarioLogado.tipoUsuario eq 'SUPERVISOR' or usuarioLogado.tipoUsuario eq 'AVALIADOR'}">
								<td width="12%">
									<a href="${path}/postagens/edit/${postagem.id}" class="btn btn-info btn-xs" role="button">Alterar</a>
								</td>
							</c:if>
							<c:if test="${usuarioLogado.tipoUsuario eq 'CIDADAO'}">
								<td width="12%">
									<a href="${path}/postagens/delete/${postagem.id}" class="btn btn-danger btn-xs" role="button">Excluir</a>
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:if>
		</div>

</div>
<c:import url="../footer.jsp" />
</body>
</html>
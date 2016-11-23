<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>

<html>
<head>
<style type="text/css">
	<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
	@IMPORT url("${path}/resources/css/bootstrap.min.css");
	@IMPORT url("${path}/resources/css/bootstrap-theme.min.css");
	@IMPORT url("${path}/resources/css/custom.css");
	@IMPORT url("${path}/resources/css/style.css");
</style>
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
	});
</script>
<c:import url="../cabecalho.jsp" />
<div class="container">
	<form:form commandName="postagem" class="form-horizontal" action="${path}/postagens/crudPostagem" method="post" enctype="multipart/form-data">
	<form:input path="id" type="hidden"/>
	<form:input path="imagem" type="hidden"/>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<div class="clearfix">
				<c:if test="${not empty postagem.id}">
 					<strong>Alterar</strong>
				</c:if>
				<c:if test="${empty postagem.id}">
 					<strong>Cadastre sua Sugestão/Reclamação</strong>
				</c:if>  					
			</div>
		</div>
  		<br>
  		<div class="panel-body">
  		
			<div class="row">
			<div class="col-sm-12">
				<label for="descricao">Descrição</label> 
				<form:textarea id="descricao" path="descricao"  class="form-control input-sm"  rows="10" autofocus="autofocus" />
				<form:errors path="descricao" cssClass="error"/>
			</div>
			</div>

			<div class="row top-buffer">
				<div class="col-sm-6">
					<label for="fileName">Imagem</label> 
					<input name="fileName" type="file" class="form-control input-sm"/>
				</div>
				<div class="col-sm-6">
					<img width="102" height="68" class="img-responsive"  src='${pageContext.request.contextPath}/postagens/imageDisplay?id=${postagem.id}' />
				</div>
			</div>
			
			<div class="row top-buffer">
	  			<div class="col-sm-3">
					<label for="area">Área</label> 
					<form:select id="area" path="area" class="form-control input-sm">
						<c:forEach items="${areas}" var="area">
							<option value="${area.id}"
								${area.id==postagem.area.id ? 'selected' : ''}>${area.descricao}							
							</option>
						</c:forEach>
					</form:select>	
					<form:errors path="area" cssClass="error"/>
				</div>
			</div>
			<c:if test="${usuarioLogado.tipoUsuario eq 'SUPERVISOR' or usuarioLogado.tipoUsuario eq 'AVALIADOR'}">
				<div class="row top-buffer">
		  			<div class="col-sm-3">
						<label for="status">Situação</label> 
						<form:select id="status" path="status" class="form-control input-sm">
							<c:forEach var="status" items="${statuss}">
									<option value="${status.descricao}"
										${status.descricao==postagem.status.descricao ? 'selected' : ''}>${status.descricao}					
									</option>
							</c:forEach>				
						</form:select>
						<form:errors path="status" cssClass="error"/>
					</div>
				</div>
			</c:if>	
			
			<c:if test="${not empty postagem.id}">
				<div class="row top-buffer">
					<div class="col-sm-12">
						<label for="comentario">Comentário</label> 
						<form:textarea id="comentario" path="comentario"  class="form-control input-sm"  rows="3" />
						<form:errors path="comentario" cssClass="error"/>
					</div>
				</div>
			</c:if>	
			<c:if test="${usuarioLogado.tipoUsuario eq 'SUPERVISOR' or usuarioLogado.tipoUsuario eq 'AVALIADOR'}">
			<div class="row top-buffer">
				<div class="col-sm-12">			
				<div class="checkbox">
					<c:if test="${not empty postagem.dataMural}">
 						<label><input checked name="mural" type="checkbox">Postar no mural</label>
					</c:if>	
					<c:if test="${empty postagem.dataMural}">
 						<label><input  name="mural" type="checkbox">Postar no mural</label>
					</c:if>					
				</div>
				</div>
			</div>
			</c:if>	
			<div class="row top-buffer">
				<div class="col-sm-12">
					<button type="submit" class="btn btn-primary">Salvar</button>
					<a href="${path}/postagens" class="btn btn-default">Cancelar</a>
				</div>
			</div> 	
			
			</div> 	
  			<div class="panel-footing">
  			<c:if test="${not empty comentarios}">
  				<div class="clearfix">
					<p class="text-center lead">Lista de Comentários</p>  				
				</div>
  				<table class="table table-hover table-condensed table-striped table-bordered">
				<thead>
					<tr>
						<th>Descrição</th>
						<th>Data</th>
						<th>Usuario</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="comentario" items="${comentarios}">
						<tr id="row${comentario.id}">
							<td>&nbsp;${fn:substring(comentario.descricao,0,80)}...</td>
							<td><fmt:formatDate value="${comentario.dataComentario}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
							<td>&nbsp;${comentario.usuario.nome}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:if>
  			</div>			
	</div>	
	</form:form>
	<br>
</div>
</body>
</html>
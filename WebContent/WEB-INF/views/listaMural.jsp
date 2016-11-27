<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>

<c:if test="${not empty postagens}">
<div class="container destaque">
	<div class="row">
		<h2 class="lead" style="color:#5bc0de;">Mural de postagens</h2>
		<hr style="width:300px; margin:0 0 20px!important; !important">	
		<c:forEach var="postagem" items="${postagens}">
		<div class="col-md-4 col-sm-6 col-xs-12">
			<div class="panel panel-default" >
				<div class="panel-heading">
					Protocolo 000${postagem.id}
				</div>
				<div class="panel-body">
					<p>${fn:substring(postagem.descricao,0,80)}...</p>
					
				</div>
				<div class="panel-footer text-center" style="min-height:170px">
					<div class="row">
						<div class="col-xs-12">Criado em: <fmt:formatDate value="${postagem.dataSugestao}" pattern="dd/MM/yyyy HH:mm:ss" /></div>
						<br>
						<div class="col-xs-6">
						Status:<br>
							${postagem.status}
						</div>
						<div class="col-xs-6">
							<img style="margin:0 auto; max-width:110px;height:50px" class="img-responsive img-rounded"  src='${pageContext.request.contextPath}/postagens/imageDisplay?id=${postagem.id}' />
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
</div>
</c:if>


<div class="container" style="display:none">
		<div class="panel panel-group">
   		<div class="panel panel-primary">
			<c:if test="${not empty postagens}">
   		   	<div class="clearfix">
				<p class="text-center lead">Mural</p>  				
			</div>			
			<table class="table table-hover table-condensed table-striped table-bordered">
				<thead>
					<tr>
						<th>Protocolo</th>
						<th>Descrição</th>
						<th>Data</th>
						<th>Situação</th>
						<th>Área</th>
						<th width="12%">Ação</th>
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
							<td width="12%">
								<a href="${path}/postagem/${postagem.id}" class="btn btn-warning btn-xs" role="button">Abrir</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:if>
		</div>
	</div>
</div>

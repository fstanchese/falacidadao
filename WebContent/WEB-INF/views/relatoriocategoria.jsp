<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="path" value="${pageContext.request.contextPath}"
	scope="request" />
<c:import url="config.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Relatorio Categoria</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	<div class="container pdt">
<h1>Relatorio Categoria</h1>
		<table class="table table-hover table-striped table-bordered text-center">
			<thead>
				<tr>
					<th>Id da Categoria</th>
					<th>Nome da Categoria</th>
					<th>Quantidade de Solicitações aprovadas</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ranking" items="${listarareas}">
					<tr>
						<th>${ranking.id} </th>
						<th>${ranking.descricao} </th> 
						<th>${ranking.contador } </th>					
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<c:import url="footer.jsp" />
</body>
</html>
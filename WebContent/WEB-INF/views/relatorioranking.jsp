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
<title>Relatorio Ranking</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	<div class="container pdt">
<h1>Relatorio Ranking</h1>
		<table class="table table-hover table-striped table-bordered text-center">
			<thead>
				<tr>
					<th>Id do Usuário</th>
					<th>Nome do Usuário</th>
					<th>Quantidade de Sugestão Feitas</th>
					<th>Quantidade Aprovadas</th>
					<th>Quantidade Reprovadas</th>
					<th>Quantidade Finalizadas</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ranking" items="${listaranking}">
					<tr>
						<td>${ranking.id}</td>
						<td>${ranking.nome}</td>
						<td>${ranking.celular} </td>
						<td>${ranking.login} </td>
						<td>${ranking.email} </td>
						<td>${ranking.senha} </td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<c:import url="footer.jsp" />
</body>
</html>
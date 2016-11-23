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

<meta charset="UTF-8">
<title>Menu Principal</title>
</head>
<body>
<c:import url="cabecalho.jsp" />
<c:import url="banner.jsp" />
<c:if test="${not empty usuarioLogado}">
	<c:import url="listaMural.jsp" />
</c:if>
<c:import url="footer.jsp" />
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    
<script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>


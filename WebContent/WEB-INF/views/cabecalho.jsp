<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Barra superior com os menus de navegação -->
<div class="container-fluid cabecalho">
	<nav class="navbar navbar-inverse">
		<div class="container menu-topo">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#menu" aria-expanded="false">
					<span class="sr-only">Menu de navegação</span> <span
						class="glyphicon glyphicon-menu-hamburger"> </span>
				</button>
				<a class="navbar-brand" href="#"></a>
			</div>

			<div class="collapse navbar-collapse" id="menu">

				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty usuarioLogado}">

						<li><a href="${path}/cadastrarusuario">Cadastre-se <i
								class="glyphicon glyphicon-edit"></i></a></li>
						<li><a href="${path}/loginForm">Entrar<i
								class="glyphicon glyphicon-log-in"></i></a></li>

					</c:if>
					<c:if test="${not empty usuarioLogado}">

						<li><a href="${path}/menu">Página Inicial</a></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown">${usuarioLogado.nome}<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="${path}/postagens">Minhas Postagens</a></li>
								<li><a href="${path}/meusdados">Meus Dados</a></li>
								<c:set var="TR1" value="SUPERVISOR" />
								<c:if test="${usuarioLogado.tipoUsuario eq TR1}">
									<li><a href="${path}/relatorio">Relatorios</a></li>
								</c:if>
								<li><a href="${path}/logout">Sair</a></li>
							</ul></li>

					</c:if>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>


</div>
package br.usjt.falacidadao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		String uri = request.getRequestURI();

		if (uri.endsWith("loginForm") 
				|| uri.endsWith("cadastrarusuario") 
				|| uri.endsWith("finalizarcadastro") 
				|| uri.endsWith("efetuaLogin") 
				|| uri.contains("css") 
				|| uri.contains("js")
				|| uri.contains("img")
				|| uri.contains("jpg")
				|| uri.contains("eot")
				|| uri.contains("svg")
				|| uri.contains("ttf")
				|| uri.contains("woff")
				|| uri.contains("woff2")
				|| uri.contains("png")){
			return true;
		}
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}
		response.sendRedirect("loginForm");
		return false;
	}
}

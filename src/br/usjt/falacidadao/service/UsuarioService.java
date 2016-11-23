package br.usjt.falacidadao.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.falacidadao.dao.UsuarioDAO;
import br.usjt.falacidadao.exception.ServiceException;
import br.usjt.falacidadao.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Transactional
	public Usuario salvar(Usuario usuario) throws ServiceException {
		Usuario usuarioExiste = buscaLogin(usuario.getLogin());
		if (usuarioExiste.getId() != null) {
			throw new ServiceException("Usuario Já Cadastrado");
		}
		return usuarioDAO.salvar(usuario);
	}
	
	public void alterar(Usuario usuario) throws ServiceException {
		usuarioDAO.alterar(usuario);
	}

	@Transactional
	public void excluir(Usuario usuario) {
		usuarioDAO.excluir(usuario);
	}
	
	public Usuario buscaLoginUsuario(Usuario usuario) {
		return usuarioDAO.buscaLoginUsuario(usuario);
	}	
	
	public Usuario buscaLogin(String login) {
		return usuarioDAO.buscaLogin(login);
	}
	
	public Usuario buscaPorId(Long id) {
		return usuarioDAO.buscaPorId(id);
	}

	public void cadastrar(Usuario usuario) {
		usuarioDAO.cadastrar(usuario);
	}
}

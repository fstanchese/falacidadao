package br.usjt.falacidadao.test;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.usjt.falacidadao.dao.AreaDAO;
import br.usjt.falacidadao.dao.UsuarioDAO;
import br.usjt.falacidadao.exception.ServiceException;
import br.usjt.falacidadao.model.Area;
import br.usjt.falacidadao.model.TipoUsuario;
import br.usjt.falacidadao.model.Usuario;
import br.usjt.falacidadao.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:WebContent/WEB-INF/spring-context.xml")
@TransactionConfiguration(defaultRollback=true)
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired 
	UsuarioDAO usuarioDAO;

	@Autowired
	AreaDAO areaDAO;
	
	@Transactional
	@Test(expected=ServiceException.class)
	public void naoDeveSalvarTest() throws ServiceException {
		Usuario usuario = new Usuario();
		Area area = new Area();
		
		area.setDescricao("teste");
		
		Area areaSalvo = areaDAO.salvar(area);	
		
		usuario.setArea(areaSalvo);
		usuario.setCelular("11 111111111");
		usuario.setEmail("teste@teste.com");
		usuario.setLogin("teste");
		usuario.setNome("teste teste");
		usuario.setSenha("123456");
		usuario.setTipoUsuario(TipoUsuario.SUPERVISOR);
		
		usuarioDAO.salvar(usuario);
		
		usuarioService.salvar(usuario);
	}
	
	@Transactional
	@Test
	public void deveSalvarTest() throws ServiceException {
		Usuario usuario = new Usuario();
		Area area = new Area();
		
		area.setDescricao("teste");
		
		Area areaSalvo = areaDAO.salvar(area);	
		
		usuario.setArea(areaSalvo);
		usuario.setCelular("11 111111111");
		usuario.setEmail("teste@teste.com");
		usuario.setLogin("teste");
		usuario.setNome("teste teste");
		usuario.setSenha("123456");
		usuario.setTipoUsuario(TipoUsuario.SUPERVISOR);
		
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		
		Assert.assertNotNull(usuarioSalvo);
	}
}

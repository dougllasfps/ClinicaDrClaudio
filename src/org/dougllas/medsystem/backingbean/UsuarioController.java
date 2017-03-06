package org.dougllas.medsystem.backingbean;

import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.dougllas.medsystem.dao.UsuarioDao;
import org.dougllas.medsystem.generic.BackingBeanGenerico;
import org.dougllas.medsystem.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("usuarioController")
@Scope("session")
public class UsuarioController extends BackingBeanGenerico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	@Autowired
	private UsuarioDao usuarioDao;
	
	public String login(){
		Usuario u = autenticar(getUsuario());
		if(u==null){
			adicionaMensagemDeErro("Usuario e/ou senha incorreto(s)");
			return null;
		}else{
			HttpSession session = getSession();
			session.setAttribute("usuario", u);
			adicionaMensagemDeInformacao("Bem vindo!");
			setUsuario(u);
			return "pacientes";
		}
	}
	
	public String logout(){
		HttpSession session = getSession();
		session.removeAttribute("usuario");
		return "login";				
	}

	private Usuario autenticar(Usuario u){
		String sql = "SELECT u FROM Usuario u WHERE u.login =:login AND u.senha =:senha ";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("login", u.getLogin());
		params.put("senha", u.getSenha());
		Usuario procurado = usuarioDao.acharPesqParam(sql, params);
		return procurado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

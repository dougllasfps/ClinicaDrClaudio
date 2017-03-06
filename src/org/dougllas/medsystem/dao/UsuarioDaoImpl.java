package org.dougllas.medsystem.dao;


import java.io.Serializable;

import org.dougllas.medsystem.generic.DaoGenericoAbstract;
import org.dougllas.medsystem.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository("usuarioDao")
public class UsuarioDaoImpl extends DaoGenericoAbstract<Usuario> implements
		UsuarioDao, Serializable{

}

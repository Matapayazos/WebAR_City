package ec.edu.ups.ar.bussiness;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ar.dao.AdministradorDAO;
import ec.edu.ups.ar.model.Administrador;


@Stateless
public class AdministradorBussiness {
	
	
	@Inject
	private AdministradorDAO dao;

	public Administrador login(String usuario, String clave) {
		return dao.login(usuario, clave);
	}

}

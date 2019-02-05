package ec.edu.ups.ar.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.ar.model.Administrador;


@Stateless
public class AdministradorDAO {
	
	
	@Inject
	private EntityManager em;

	
	public Administrador login(String usuario, String clave) {
		String jpql = "SELECT a FROM Administrador a WHERE a.usuario LIKE ?1 AND a.clave LIKE ?2";
		Query query = em.createQuery(jpql, Administrador.class);
		query.setParameter(1, usuario);
		query.setParameter(2, clave);
		Administrador a = (Administrador) query.getSingleResult();
		return a;
	}

}

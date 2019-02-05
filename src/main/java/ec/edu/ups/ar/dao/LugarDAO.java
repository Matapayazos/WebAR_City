package ec.edu.ups.ar.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.ar.model.Lugar;

@Stateless
public class LugarDAO {
	@Inject
	private EntityManager em;

	public void insert(Lugar lugar) {
		em.persist(lugar);
	}
	
	public void update(Lugar lugar) {
		em.merge(lugar);
	}
	
	public void remove(String id) {
		em.remove(this.read(id));
	}
	
	public Lugar read(String id) {
		Lugar lugar = em.find(Lugar.class, id);
		return lugar;
	}
	
	public List<Lugar> getLugar(){
		String jpql = "SELECT l FROM Lugar l";
		Query query = em.createQuery(jpql, Lugar.class);
		List<Lugar> listado = query.getResultList();		
		return listado;
	}
}

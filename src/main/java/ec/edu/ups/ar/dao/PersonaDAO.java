package ec.edu.ups.ar.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.ar.model.Persona;

@Stateless
public class PersonaDAO {
	@Inject
	private EntityManager em;

	public void insert(Persona persona) {
		em.persist(persona);
	}
	
	public void update(Persona persona) {
		em.merge(persona);
	}
	
	public void remove(String id) {
		em.remove(this.read(id));
	}
	
	public Persona read(String id) {
		Persona persona = em.find(Persona.class, id);
		return persona;
	}
	
	public List<Persona> getPersonas(){
		String jpql = "SELECT p FROM Persona p";
		Query query = em.createQuery(jpql, Persona.class);
		List<Persona> listado = query.getResultList();		
		return listado;
	}
	


    

	
}

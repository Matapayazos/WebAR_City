package ec.edu.ups.ar.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.ar.model.Item;

@Stateless
public class ItemDAO {
	@Inject
	private EntityManager em;

	public void insert(Item item) {
		em.persist(item);
	}
	
	public void update(Item item) {
		em.merge(item);
	}
	
	public void remove(String id) {
		em.remove(this.read(id));
	}
	
	public Item read(String id) {
		Item item = em.find(Item.class, id);
		return item;
	}
	
	public List<Item> getItem(){
		String jpql = "SELECT i FROM Item i";
		Query query = em.createQuery(jpql, Item.class);
		List<Item> listado = query.getResultList();		
		return listado;
	}
}

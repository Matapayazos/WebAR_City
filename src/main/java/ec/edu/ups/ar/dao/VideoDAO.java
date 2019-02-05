package ec.edu.ups.ar.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.ar.model.Video;


@Stateless
public class VideoDAO {
	@Inject
	private EntityManager em;

	public void insert(Video video) {
		em.persist(video);
	}
	
	public void update(Video video) {
		em.merge(video);
	}
	
	public void remove(String id) {
		em.remove(this.read(id));
	}
	
	public Video read(String id) {
		Video video = em.find(Video.class, id);
		return video;
	}
	
	public List<Video> getVideo(){
		String jpql = "SELECT v FROM Video v";
		Query query = em.createQuery(jpql, Video.class);
		List<Video> listado = query.getResultList();		
		return listado;
	}
}

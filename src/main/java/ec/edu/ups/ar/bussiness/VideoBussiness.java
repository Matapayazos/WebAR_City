package ec.edu.ups.ar.bussiness;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ar.dao.VideoDAO;
import ec.edu.ups.ar.model.Video;

@Stateless
public class VideoBussiness {
	@Inject
	private VideoDAO dao;
	
	
	public boolean save(Video video) throws Exception {
		Video aux = dao.read(video.getCodigo());
		if(aux!=null)
			throw new Exception("Video ya existe");
		else
			dao.insert(video);	
		
		return true;
	}
	
	public List<Video> getListadoVideo(){
		return dao.getVideo();
	}
	
	public void eliminar(String cedula) throws Exception {
		Video aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Video no existe");
		else
			dao.remove(cedula);
	}
	
	public Video getVideo(String cedula) throws Exception {
		Video aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Video no existe");
		else
			return aux;
	}
	
	public void actualizar(Video video) throws Exception {
		Video aux = dao.read(video.getCodigo());
		if(aux==null)
			throw new Exception("Video no existe");
		else
			dao.update(video);
	}
}

package ec.edu.ups.ar.bussiness;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ar.dao.HistoriaDAO;
import ec.edu.ups.ar.model.Historia;


@Stateless
public class HistoriaBussiness {
	@Inject
	private HistoriaDAO dao;
	
	
	public boolean save(Historia historia) throws Exception {
		Historia aux = dao.read(historia.getCodigo());
		if(aux!=null)
			throw new Exception("Historia ya existe");
		else
			dao.insert(historia);	
		
		return true;
	}
	
	public List<Historia> getListadoHistoria(){
		return dao.getHistoria();
	}
	
	public void eliminar(String cedula) throws Exception {
		Historia aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Historia no existe");
		else
			dao.remove(cedula);
	}
	
	public Historia getHistoria(String cedula) throws Exception {
		Historia aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Historia no existe");
		else
			return aux;
	}
	
	public void actualizar(Historia historia) throws Exception {
		Historia aux = dao.read(historia.getCodigo());
		if(aux==null)
			throw new Exception("Historia no existe");
		else
			dao.update(historia);
	}
}

package ec.edu.ups.ar.bussiness;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ar.dao.LugarDAO;
import ec.edu.ups.ar.model.Lugar;



@Stateless
public class LugarBussiness {
	
	@Inject
	private LugarDAO dao;
	
	
	public boolean save(Lugar lugar) throws Exception {
		Lugar aux = dao.read(lugar.getCodigo());
		if(aux!=null)
			throw new Exception("Lugar ya existe");
		else
			dao.insert(lugar);	
		
		return true;
	}
	
	public List<Lugar> getListadoLugar(){
		return dao.getLugar();
	}
	
	public void eliminar(String cedula) throws Exception {
		Lugar aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Lugar no existe");
		else
			dao.remove(cedula);
	}
	
	public Lugar getLugar(String cedula) throws Exception {
		Lugar aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Lugar no existe");
		else
			return aux;
	}
	
	public void actualizar(Lugar lugar) throws Exception {
		Lugar aux = dao.read(lugar.getCodigo());
		if(aux==null)
			throw new Exception("Lugar no existe");
		else
			dao.update(lugar);
	}

}

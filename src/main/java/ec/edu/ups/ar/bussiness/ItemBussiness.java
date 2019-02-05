package ec.edu.ups.ar.bussiness;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Entity;

import ec.edu.ups.ar.dao.ItemDAO;
import ec.edu.ups.ar.model.Item;

@Stateless
public class ItemBussiness {
	@Inject
	private ItemDAO dao;
	
	
	public boolean save(Item item) throws Exception {
		Item aux = dao.read(item.getCodigo());
		if(aux!=null)
			throw new Exception("Item ya existe");
		else
			dao.insert(item);	
		
		return true;
	}
	
	public List<Item> getListadoItem(){
		return dao.getItem();
	}
	
	public void eliminar(String cedula) throws Exception {
		Item aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Item no existe");
		else
			dao.remove(cedula);
	}
	
	public Item getItem(String cedula) throws Exception {
		Item aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Item no existe");
		else
			return aux;
	}
	
	public void actualizar(Item item) throws Exception {
		Item aux = dao.read(item.getCodigo());
		if(aux==null)
			throw new Exception("Item no existe");
		else
			dao.update(item);
	}

}

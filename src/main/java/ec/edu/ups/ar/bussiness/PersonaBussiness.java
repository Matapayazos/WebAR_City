package ec.edu.ups.ar.bussiness;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.ar.dao.PersonaDAO;
import ec.edu.ups.ar.model.Persona;
import ec.edu.ups.ar.util.ValidarCedula;


@Stateless
public class PersonaBussiness {

	@Inject
	private PersonaDAO dao;
	
	ValidarCedula vc = new ValidarCedula();
	
	public boolean save(Persona persona) throws Exception {
		
		//Persona validacion = dao.read(persona.getCedula());
		
		Persona aux = dao.read(persona.getCedula());
		
		if(aux!=null)
			throw new Exception("Persona ya existe");
		else
			dao.insert(persona);	
		
		return true;
	}
	
	public List<Persona> getListadoPersona(){
		return dao.getPersonas();
	}
	
	public void eliminar(String cedula) throws Exception {
		Persona aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Persona no existe");
		else
			dao.remove(cedula);
	}
	
	public Persona getPersona(String cedula) throws Exception {
		Persona aux = dao.read(cedula);
		if(aux==null)
			throw new Exception("Persona no existe");
		else
			return aux;
	}
	
	public void actualizar(Persona persona) throws Exception {
		Persona aux = dao.read(persona.getCedula());
		if(aux==null)
			throw new Exception("Persona no existe");
		else
			dao.update(persona);
	}
}

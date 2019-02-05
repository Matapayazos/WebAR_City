package ec.edu.ups.ar.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.ar.bussiness.PersonaBussiness;
import ec.edu.ups.ar.model.Persona;


@ManagedBean
@ViewScoped
public class PersonaBean {
	@Inject
	private PersonaBussiness perBussiness;

	@Inject
	private FacesContext facesContext;

	private Persona newPersona;

	private String id; // Parametro para edicion

	private List<Persona> personas;



	@PostConstruct
	public void init() {
		newPersona = new Persona();
		personas = perBussiness.getListadoPersona();
	}

	public void loadData() {

		System.out.println("load data " + id);
		if (id == null)
			return;
		try {
			newPersona = perBussiness.getPersona(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public Persona getNewPersona() {
		return newPersona;
	}

	public void setNewPersona(Persona newPersona) {
		this.newPersona = newPersona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		System.out.println("id param " + id);
		this.id = id;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public String guardar() {
		try {
			perBussiness.save(newPersona);
			System.out.println("Registro guardado");
			return "Listar_Persona?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al guardar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	public String eliminar(String cedula) {

		try {
			perBussiness.eliminar(cedula);
			System.out.println("Registro eliminado");
			return "Listar_Persona?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	public String editar(Persona persona) {
		System.out.println(persona);
		return "Crear_Persona?faces-redirect=true&id=" + persona.getCedula();
	}

}

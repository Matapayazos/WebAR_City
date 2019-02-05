package ec.edu.ups.ar.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.ar.bussiness.HistoriaBussiness;
import ec.edu.ups.ar.model.Historia;

@ManagedBean
@ViewScoped
public class HistoriaBean {
	@Inject
	private HistoriaBussiness hisBussiness;

	@Inject
	private FacesContext facesContext;

	private Historia newHistoria;

	
	private String id; // Parametro para edicion

	private List<Historia> historias;
	
	@PostConstruct
	public void init() {
		newHistoria = new Historia();
		historias = hisBussiness.getListadoHistoria();
	}

	public void loadData() {

		System.out.println("load data " + id);
		if (id == null)
			return;
		try {
			newHistoria = hisBussiness.getHistoria(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public Historia getNewHistoria() {
		return newHistoria;
	}

	public void setNewHistoria(Historia newHistoria) {
		this.newHistoria = newHistoria;
	}

	public List<Historia> getHistorias() {
		return historias;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		System.out.println("id param " + id);
		this.id = id;
	}

	public void setHistorias(List<Historia> historias) {
		this.historias = historias;
	}

	public String guardar() {
	try {
				hisBussiness.save(newHistoria);
			System.out.println("Registro guardado");
			return "Listar_Historia?faces-redirect=true";
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
			hisBussiness.eliminar(cedula);
			System.out.println("Registro eliminado");
			return "Listar_Historia?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	public String editar(Historia historia) {
		System.out.println(historia);
		return "Crear_Historia?faces-redirect=true&id=" + historia.getCodigo();
	}

}

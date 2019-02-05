package ec.edu.ups.ar.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.ar.bussiness.LugarBussiness;
import ec.edu.ups.ar.model.Imagen;
import ec.edu.ups.ar.model.Lugar;


@ManagedBean
@ViewScoped
public class LugarBean {
	@Inject
	private LugarBussiness lugBussiness;

	@Inject
	private FacesContext facesContext;

	private Lugar newLugar;

	private String id; // Parametro para edicion

	private List<Lugar> lugars;

	@PostConstruct
	public void init() {
		newLugar = new Lugar();
		newLugar.addImagen(new Imagen());
		lugars = lugBussiness.getListadoLugar();
	}

	public void loadData() {

		System.out.println("load data " + id);
		if (id == null)
			return;
		try {
			newLugar = lugBussiness.getLugar(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public Lugar getNewLugar() {
		return newLugar;
	}

	public void setNewLugar(Lugar newLugar) {
		this.newLugar = newLugar;
	}

	public List<Lugar> getLugars() {
		return lugars;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		System.out.println("id param " + id);
		this.id = id;
	}

	public void setLugars(List<Lugar> lugars) {
		this.lugars = lugars;
	}

	public String guardar() {
		try {
				lugBussiness.save(newLugar);
			System.out.println("Registro guardado");
			return "Listar_Lugar?faces-redirect=true";
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
			lugBussiness.eliminar(cedula);
			System.out.println("Registro eliminado");
			return "Listar_Lugar?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	public String editar(Lugar lugar) {
		System.out.println(lugar);
		System.out.println("wstndoddd");
		return "Crear_Lugar?faces-redirect=true&id=" + lugar.getCodigo();
	}
	public String addImagen() {
		newLugar.addImagen(new Imagen());
		return null;
	}
}

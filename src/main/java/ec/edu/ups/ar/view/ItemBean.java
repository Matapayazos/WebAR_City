package ec.edu.ups.ar.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.ar.bussiness.ItemBussiness;
import ec.edu.ups.ar.model.Item;


@ManagedBean
@ViewScoped
public class ItemBean {
	@Inject
	private ItemBussiness iteBussiness;

	@Inject
	private FacesContext facesContext;

	private Item newItem;

	private String id; // Parametro para edicion

	private List<Item> items;

	@PostConstruct
	public void init() {
		newItem = new Item();
		items = iteBussiness.getListadoItem();
	}

	public void loadData() {

		System.out.println("load data " + id);
		if (id == null)
			return;
		try {
			newItem = iteBussiness.getItem(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public Item getNewItem() {
		return newItem;
	}

	public void setNewItem(Item newItem) {
		this.newItem = newItem;
	}

	public List<Item> getItems() {
		return items;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		System.out.println("id param " + id);
		this.id = id;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String guardar() {
		try {
				iteBussiness.save(newItem);
			System.out.println("Registro guardado");
			return "Listar_Item?faces-redirect=true";
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
			iteBussiness.eliminar(cedula);
			System.out.println("Registro eliminado");
			return "Listar_Item?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	public String editar(Item item) {
		System.out.println(item);
		return "Crear_Item?faces-redirect=true&id=" + item.getCodigo();
	}

}

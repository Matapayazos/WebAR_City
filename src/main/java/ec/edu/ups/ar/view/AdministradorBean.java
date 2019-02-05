package ec.edu.ups.ar.view;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.ar.bussiness.AdministradorBussiness;
import ec.edu.ups.ar.model.Administrador;

@ManagedBean
@Stateful
public class AdministradorBean {
	
	
	@Inject
	private AdministradorBussiness admB;
	
	@Inject
	private FacesContext facesContext;
	private Administrador administrador;

	private String usuario;
	private String clave;
	private int id;

	public String login() {
		try {
			if (admB.login(usuario, clave) != null) {
				administrador = admB.login(usuario, clave);
				System.out.println("Se logeo al sistema");
				System.out.println("CODIGOOOOOOO>>> " + administrador.getId_cliente());
				return "principal?faces-redirect=true&id=" + administrador.getId_cliente();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Error";
	}

	
	public AdministradorBussiness getAdmB() {
		return admB;
	}

	public void setAdmB(AdministradorBussiness admB) {
		this.admB = admB;
	}


	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}

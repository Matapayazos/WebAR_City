package ec.edu.ups.ar.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.ar.bussiness.VideoBussiness;
import ec.edu.ups.ar.model.Video;

@ManagedBean
@ViewScoped
public class VideoBean {
	@Inject
	private VideoBussiness vidBussiness;

	@Inject
	private FacesContext facesContext;

	private Video newVideo;

	private String id; // Parametro para edicion

	private List<Video> videos;

	private boolean editing;

	@PostConstruct
	public void init() {
		newVideo = new Video();
		editing = false;
		videos = vidBussiness.getListadoVideo();
	}

	public void loadData() {

		System.out.println("load data " + id);
		if (id == null)
			return;
		try {
			newVideo = vidBussiness.getVideo(id);
			editing = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}

	public Video getNewVideo() {
		return newVideo;
	}

	public void setNewVideo(Video newVideo) {
		this.newVideo = newVideo;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		System.out.println("id param " + id);
		this.id = id;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public String guardar() {
		System.out.println("editando " + editing);
		try {
			if (editing)
				vidBussiness.actualizar(newVideo);
			else
				vidBussiness.save(newVideo);
			System.out.println("Registro guardado");
			return "Listar_Video?faces-redirect=true";
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
			vidBussiness.eliminar(cedula);
			System.out.println("Registro eliminado");
			return "List_Video?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al eliminar");
			e.printStackTrace();

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
		return null;
	}

	public String editar(Video video) {
		editing = true;
		System.out.println(video);
		return "Crear_Video?faces-redirect=true&id=" + video.getCodigo();
	}

}

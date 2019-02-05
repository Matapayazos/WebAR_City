package ec.edu.ups.ar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ec.edu.ups.ar.bussiness.LugarBussiness;
import ec.edu.ups.ar.model.Lugar;

@Path("/Lugares")
public class LugarServiceRest {
	@Inject
	private LugarBussiness lBussiness;
	
	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Lugar> getHistoria(){
		return lBussiness.getListadoLugar();
	}
	
	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertHistoria(Lugar lugar ) {
		
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			lBussiness.save(lugar);
			data.put("code", "1");
			data.put("message", "OK");
			builder=Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder=Response.status(Response.Status.OK).entity(data);
		}
		
		return builder.build();
	}
	@PUT
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	public Response actualizarPersona(Lugar lugar ) {
		
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			lBussiness.actualizar(lugar);
			data.put("code", "2");
			data.put("message", "OK");
			builder=Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("code", "100");
			data.put("message", e.getMessage());
			builder=Response.status(Response.Status.OK).entity(data);
		}
		
		return builder.build();
	}

}

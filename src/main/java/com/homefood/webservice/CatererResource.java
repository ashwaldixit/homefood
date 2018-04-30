package com.homefood.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.Caterer;
import com.homefood.service.CatererService;

@Path("/caterers")
public class CatererResource {

	@Autowired
	CatererService catererService;

	@GET
	@Path("/{catererid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("catererid") long catererid) {
		return Response.ok().entity(catererService.readById(catererid)).build();
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByName(@PathParam("name") String name) {
		return Response.ok().entity(catererService.readActiveByName(name)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCaterer(Caterer caterer) {
		return Response.ok().entity(catererService.validateAndCreate(caterer)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCaterer(Caterer caterer) {
		return Response.ok().entity(catererService.update(caterer)).build();
	}

}

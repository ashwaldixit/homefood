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

import com.homefood.model.ProductPresence;
import com.homefood.service.ProductPresenceService;

@Path("/productpresence")
public class ProductPresenceResource {

	@Autowired
	ProductPresenceService productPresenceService;

	@GET
	@Path("/{productPresenceid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("productPresenceid") long productPresenceid) {
		return Response.ok().entity(productPresenceService.readById(productPresenceid)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProductPresence(ProductPresence productPresence) {
		return Response.ok().entity(productPresenceService.validateAndCreate(productPresence)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProductPresence(ProductPresence productPresence) {
		return Response.ok().entity(productPresenceService.update(productPresence)).build();
	}

}

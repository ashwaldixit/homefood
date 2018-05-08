package com.homefood.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.Caterer;
import com.homefood.model.CatererLocation;
import com.homefood.model.User;
import com.homefood.model.UserAuthenticationToken;
import com.homefood.service.CatererLocationService;
import com.homefood.service.CatererService;
import com.homefood.service.UserAuthenticationTokenService;

@Path("/caterers")
public class CatererResource {

	@Context
	private javax.servlet.http.HttpServletRequest req;

	@Autowired
	UserAuthenticationTokenService authenticationTokenService;

	@Autowired
	CatererService catererService;

	@Autowired
	CatererLocationService catererLocationService;

	@GET
	@Path("/{catererid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("catererid") long catererid) {
		return Response.ok().entity(catererService.readById(catererid)).build();
	}

	@GET
	@Path("/active")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActive() {
		return Response.ok().entity(catererService.readAllInActiveCaterers()).build();
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

	@GET
	@Path("/active/locations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActiveLocations() {
		return Response.ok().entity(catererService.getAllActiveLocations(catererService.getByUser(getUser()))).build();
	}

	private User getUser() {
		String token = req.getHeader("token");
		UserAuthenticationToken authToken = authenticationTokenService.findByToken(token);
		if (null != authToken)
			return authToken.getUser();
		return null;

	}

	@POST
	@Path("/location")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCatererLocation(CatererLocation catererLocation) {
		catererLocation.setCaterer(catererService.getByUser(getUser()));
		return Response.ok().entity(catererLocationService.validateAndCreate(catererLocation)).build();
	}

}

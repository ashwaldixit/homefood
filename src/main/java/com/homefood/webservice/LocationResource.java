package com.homefood.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.Location;
import com.homefood.service.LocationService;

@Path("/locations")
public class LocationResource {

	@Context
	private javax.servlet.http.HttpServletRequest req;

	@Autowired
	LocationService locationService;

	@GET
	@Path("/district/{name}/active")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActive(@PathParam("name") String name) {
		return Response.ok().entity(locationService.readByDistrict(name)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLocation(Location location) {
		return Response.ok().entity(locationService.validateAndCreate(location)).build();
	}

}

package com.homefood.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.model.CatererLocation;
import com.homefood.service.CatererLocationService;
import com.homefood.service.CatererService;

@Path("/catererlocation")
public class CatererLocationResourcce {

	@Autowired
	CatererLocationService catererLocationService;

	@Autowired
	CatererService catererService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCatererLocation(CatererLocation catererLocation) {
		return Response.ok().entity(catererLocationService.createCaterer(catererLocation)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getByCatererId(long catererId) {
		return Response.ok().entity(catererLocationService.readAllActiveByCaterer(catererService.readById(catererId)))
				.build();
	}

}

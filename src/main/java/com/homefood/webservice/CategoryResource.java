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

import com.homefood.model.Category;
import com.homefood.service.CategoryService;

@Path("/categories")
public class CategoryResource {

	@Autowired
	CategoryService categoryService;

	@GET
	@Path("/{categoryid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("categoryid") long categoryid) {
		return Response.ok().entity(categoryService.readById(categoryid)).build();
	}

	@GET
	@Path("/name/active/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveByName(@PathParam("name") String name) {
		return Response.ok().entity(categoryService.readActiveByName(name)).build();
	}

	@GET
	@Path("/name/inactive/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInactiveByName(@PathParam("name") String name) {
		return Response.ok().entity(categoryService.readAllInActiveByName(name)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCategory(Category category) {
		return Response.ok().entity(categoryService.validateAndCreate(category)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCategory(Category category) {
		return Response.ok().entity(categoryService.update(category)).build();
	}

}

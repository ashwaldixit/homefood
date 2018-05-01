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

import com.homefood.model.Customer;
import com.homefood.service.CustomerService;

@Path("/customers")
public class CustomerResource {

	@Autowired
	CustomerService customerService;

	@GET
	@Path("/{customerid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("customerid") long customerid) {
		return Response.ok().entity(customerService.readById(customerid)).build();
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByName(@PathParam("name") String name) {
		return Response.ok().entity(customerService.readByUserName(name)).build();
	}

	@GET
	@Path("/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByEmail(@PathParam("email") String email) {
		return Response.ok().entity(customerService.readByEmail(email)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer customer) {
		return Response.ok().entity(customerService.validateAndCreateCustomer(customer)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(Customer customer) {
		return Response.ok().entity(customerService.update(customer)).build();
	}

}

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

import com.homefood.model.CustomerOrder;
import com.homefood.model.User;
import com.homefood.model.UserAuthenticationToken;
import com.homefood.service.CustomerOrderService;
import com.homefood.service.UserAuthenticationTokenService;
import com.homefood.service.UserService;

@Path("/customerorders")
public class CustomerOrderResource {

	@Context
	private javax.servlet.http.HttpServletRequest req;

	@Autowired
	CustomerOrderService customerOrderService;

	@Autowired
	UserService customerService;

	@Autowired
	UserAuthenticationTokenService authenticationTokenService;

	@GET
	@Path("/{customerorderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("orderid") long orderid) {
		return Response.ok().entity(customerOrderService.readById(orderid)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(CustomerOrder customerOrder) {
		customerOrder.setCustomer(getUser());
		return Response.ok().entity(customerOrderService.validateAndCreate(customerOrder)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(CustomerOrder customerOrder) {
		return Response.ok().entity(customerOrderService.update(customerOrder)).build();
	}

	@GET
	@Path("/customer/{customerid}/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOpenOrderOFCustomer(@PathParam("customerid") long id) {
		return Response.ok().entity(customerOrderService.readAllOpenOrdersOfCustomer(customerService.readById(id)))
				.build();
	}

	@GET
	@Path("/customer/{customerid}/cancelled")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCancelOrderOFCustomer(@PathParam("customerid") long id) {
		return Response.ok().entity(customerOrderService.readAllCancelledOrdersOfCustomer(customerService.readById(id)))
				.build();
	}

	@GET
	@Path("/customer/{customerid}/delivered")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDeliveredOrderOFCustomer(@PathParam("customerid") long id) {
		return Response.ok().entity(customerOrderService.readAllDeliveredOrdersOfCustomer(customerService.readById(id)))
				.build();
	}

	@GET
	@Path("/customer/{customerid}/confirmed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllConfirmedOrderOFCustomer(@PathParam("customerid") long id) {
		return Response.ok().entity(customerOrderService.readAllConfirmedOrdersOfCustomer(customerService.readById(id)))
				.build();
	}

	private User getUser() {
		String token = req.getHeader("token");
		UserAuthenticationToken authToken = authenticationTokenService.findByToken(token);
		if (null != authToken)
			return authToken.getUser();
		return null;

	}
}

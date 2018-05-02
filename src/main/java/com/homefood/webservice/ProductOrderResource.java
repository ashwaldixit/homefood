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

import com.homefood.model.ProductOrder;
import com.homefood.service.CustomerService;
import com.homefood.service.ProductOrderService;
import com.homefood.service.ProductService;

@Path("/productorders")
public class ProductOrderResource {

	@Autowired
	ProductOrderService orderService;

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@GET
	@Path("/{orderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("orderid") long orderid) {
		return Response.ok().entity(orderService.readById(orderid)).build();
	}

	@GET
	@Path("/customer/{customerid}/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOpenOrderOFCustomer(@PathParam("name") long id) {
		return Response.ok().entity(orderService.readAllOpenOrdersOfCustomer(customerService.readById(id))).build();
	}

	@GET
	@Path("/customer/{customerid}/cancelled")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCancelOrderOFCustomer(@PathParam("name") long id) {
		return Response.ok().entity(orderService.readAllCancelledOrdersOfCustomer(customerService.readById(id)))
				.build();
	}

	@GET
	@Path("/customer/{customerid}/delivered")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDeliveredOrderOFCustomer(@PathParam("name") long id) {
		return Response.ok().entity(orderService.readAllDeliveredOrdersOfCustomer(customerService.readById(id)))
				.build();
	}

	@GET
	@Path("/customer/{customerid}/confirmed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllConfirmedOrderOFCustomer(@PathParam("name") long id) {
		return Response.ok().entity(orderService.readAllConfirmedOrdersOfCustomer(customerService.readById(id)))
				.build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(ProductOrder order) {
		return Response.ok().entity(orderService.validateAndCreate(order)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(ProductOrder order) {
		return Response.ok().entity(orderService.update(order)).build();
	}
}

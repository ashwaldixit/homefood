package com.homefood.webservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import com.homefood.codetype.NotificationInfo;
import com.homefood.codetype.OrderStatus;
import com.homefood.core.LocalDateTimeParser;
import com.homefood.core.TransactionInfo;
import com.homefood.model.Order;
import com.homefood.service.CustomerService;
import com.homefood.service.OrderService;
import com.homefood.service.ProductService;

@Path("/orders")
public class OrderResource {

	@Autowired
	OrderService orderService;

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	@Autowired
	TransactionInfo transactionInfo;

	@Autowired
	LocalDateTimeParser dateTimeParser;

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
	public Response createOrder(Order order) {
		return Response.ok().entity(orderService.validateAndCreate(order)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(Order order) {
		return Response.ok().entity(orderService.update(order)).build();
	}

	@GET
	@Path("/customer/{customerid}/product/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProductsOFCustomer(@PathParam("customerid") long customerid,
			@PathParam("productid") long productid) {
		return Response.ok().entity(orderService.readAllByCustomerAndProduct(customerService.readById(customerid),
				productService.readById(productid))).build();
	}

	@GET
	@Path("/product/{productid}/date/{deliveryDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProductsByDeliveryDate(@PathParam("productid") long productid,
			@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryLocalDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(
				orderService.readAllByProductAndDeliveryDate(productService.readById(productid), deliveryLocalDateTime))
				.build();
	}

	@GET
	@Path("/date/{deliveryDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByDeliveryDate(@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(orderService.readAllByDeliveryDate(deliveryDateTime)).build();
	}

	@GET
	@Path("/date/{deliveryDate}/confirmed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllConfirmedOrdersByDeliveryDate(@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(orderService.readAllConfirmedByDeliveryDate(deliveryDateTime)).build();
	}

	@GET
	@Path("/date/{deliveryDate}/delivered")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDeliveredOrdersByDeliveryDate(@PathParam("deliveryDate") LocalDateTime deliveryDate) {
		return Response.ok().entity(orderService.readAllDeliveredByDeliveryDate(deliveryDate)).build();
	}

	@GET
	@Path("/date/{deliveryDate}/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOpenOrdersByDeliveryDate(@PathParam("deliveryDate") LocalDateTime deliveryDate) {
		return Response.ok().entity(orderService.readAllOpenByDeliveryDate(deliveryDate)).build();
	}

	@GET
	@Path("/date/{deliveryDate}/cancelled")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCancelledOrdersByDeliveryDate(@PathParam("deliveryDate") LocalDateTime deliveryDate) {
		return Response.ok().entity(orderService.readAllCancelledByDeliveryDate(deliveryDate)).build();
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/status/{orderStatus}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrdersByProductAndDeliveryDateAndOrderStatus(@PathParam("productId") long productId,
			@PathParam("deliveryDate") LocalDateTime deliveryDate, @PathParam("status") String orderStatus) {
		for (OrderStatus status : OrderStatus.values()) {
			if (orderStatus.equalsIgnoreCase(status.name())) {
				return Response.ok().entity(orderService.readAllByProductAndDeliveryDateAndOrderStatus(
						productService.readById(productId), deliveryDate, status)).build();
			}
		}
		List<Object> args = new ArrayList<Object>();
		args.add(OrderStatus.class.getSimpleName());
		transactionInfo.generateException("INVALID_FIELD", args, NotificationInfo.ERROR, 500);
		return null;
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/confirmed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllConfirmedOrdersByProductAndDeliveryDate(@PathParam("productId") long productId,
			@PathParam("deliveryDate") LocalDateTime deliveryDate) {
		return Response.ok().entity(
				orderService.readAllConfirmedByProductAndDeliveryDate(productService.readById(productId), deliveryDate))
				.build();
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/delivered")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDeliveredOrdersByProductAndDeliveryDate(@PathParam("productId") long productId,
			@PathParam("deliveryDate") LocalDateTime deliveryDate) {
		return Response.ok().entity(
				orderService.readAllDeliveredByProductAndDeliveryDate(productService.readById(productId), deliveryDate))
				.build();
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/cancelled")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCancelledOrdersByProductAndDeliveryDate(@PathParam("productId") long productId,
			@PathParam("deliveryDate") LocalDateTime deliveryDate) {
		return Response.ok().entity(
				orderService.readAllCancelledByProductAndDeliveryDate(productService.readById(productId), deliveryDate))
				.build();
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOpenOrdersByProductAndDeliveryDate(@PathParam("productId") long productId,
			@PathParam("deliveryDate") LocalDateTime deliveryDate) {
		return Response.ok().entity(
				orderService.readAllOpenByProductAndDeliveryDate(productService.readById(productId), deliveryDate))
				.build();
	}
}

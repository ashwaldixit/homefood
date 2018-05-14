package com.homefood.webservice;

import java.lang.reflect.Field;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.homefood.codetype.NotificationInfo;
import com.homefood.codetype.OrderStatus;
import com.homefood.core.LocalDateTimeParser;
import com.homefood.core.TransactionInfo;
import com.homefood.model.ProductOrder;
import com.homefood.model.User;
import com.homefood.model.UserAuthenticationToken;
import com.homefood.service.CatererService;
import com.homefood.service.ProductOrderService;
import com.homefood.service.ProductService;
import com.homefood.service.UserAuthenticationTokenService;
import com.homefood.service.UserService;

@Path("/productorders")
public class ProductOrderResource {

	@Context
	private javax.servlet.http.HttpServletRequest req;

	@Autowired
	UserAuthenticationTokenService authenticationTokenService;

	@Autowired
	CatererService catererService;

	@Autowired
	ProductOrderService orderService;

	@Autowired
	UserService customerService;

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
	public Response getAllDeliveredOrdersByDeliveryDate(@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(orderService.readAllDeliveredByDeliveryDate(deliveryDateTime)).build();
	}

	@GET
	@Path("/date/{deliveryDate}/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOpenOrdersByDeliveryDate(@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(orderService.readAllOpenByDeliveryDate(deliveryDateTime)).build();
	}

	@GET
	@Path("/date/{deliveryDate}/cancelled")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCancelledOrdersByDeliveryDate(@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(orderService.readAllCancelledByDeliveryDate(deliveryDateTime)).build();
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/status/{orderStatus}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrdersByProductAndDeliveryDateAndOrderStatus(@PathParam("productId") long productId,
			@PathParam("deliveryDate") String deliveryDate, @PathParam("status") String orderStatus) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		for (OrderStatus status : OrderStatus.values()) {
			if (orderStatus.equalsIgnoreCase(status.name())) {
				return Response.ok().entity(orderService.readAllByProductAndDeliveryDateAndOrderStatus(
						productService.readById(productId), deliveryDateTime, status)).build();
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
			@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(orderService
				.readAllConfirmedByProductAndDeliveryDate(productService.readById(productId), deliveryDateTime))
				.build();
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/delivered")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDeliveredOrdersByProductAndDeliveryDate(@PathParam("productId") long productId,
			@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(orderService
				.readAllDeliveredByProductAndDeliveryDate(productService.readById(productId), deliveryDateTime))
				.build();
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/cancelled")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCancelledOrdersByProductAndDeliveryDate(@PathParam("productId") long productId,
			@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(orderService
				.readAllCancelledByProductAndDeliveryDate(productService.readById(productId), deliveryDateTime))
				.build();
	}

	@GET
	@Path("/product/{productId}/date/{deliveryDate}/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOpenOrdersByProductAndDeliveryDate(@PathParam("productId") long productId,
			@PathParam("deliveryDate") String deliveryDate) {
		LocalDateTime deliveryDateTime = dateTimeParser.parseString(deliveryDate);
		return Response.ok().entity(
				orderService.readAllOpenByProductAndDeliveryDate(productService.readById(productId), deliveryDateTime))
				.build();
	}

	@GET
	@Path("/bydate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByDate(@QueryParam("size") int size, @QueryParam("index") int index,
			@QueryParam("sortas") String sortas, @QueryParam("sortby") String sortBy,
			@QueryParam("adddays") int addDays) {

		addDays = addDays > 7 ? 7 : addDays;
		LocalDateTime deliveryDateTime = LocalDateTime.now().plusDays(addDays);
		size = size > 10 ? size : 10;
		Direction direction = (null != sortas && !sortas.isEmpty() && sortas.equalsIgnoreCase("asc")) ? Direction.ASC
				: Direction.DESC;

		if (null == sortBy || sortBy.isEmpty()) {
			sortBy = "orderStatus";
		} else {
			for (Field x : ProductOrder.class.getDeclaredFields()) {
				if (x.getName().equalsIgnoreCase(sortBy)) {
					sortBy = x.getName();
					break;
				}
			}
		}

		@SuppressWarnings("deprecation")
		PageRequest pageable = new PageRequest(index, size, direction, sortBy);
		return Response.ok().entity(orderService.readAllByDeliverydateAndProductCaterer(deliveryDateTime,
				catererService.getByUser(getUser()), pageable)).build();

	}

	private User getUser() {
		String token = req.getHeader("token");
		UserAuthenticationToken authToken = authenticationTokenService.findByToken(token);
		if (null != authToken)
			return authToken.getUser();
		return null;

	}

}

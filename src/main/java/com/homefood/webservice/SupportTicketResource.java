package com.homefood.webservice;

import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.homefood.codetype.SupportTicketStatus;
import com.homefood.model.SupportTicket;
import com.homefood.service.SupportTicketService;
import com.homefood.service.UserService;

@Path("/supporttickets")
public class SupportTicketResource {

	@Autowired
	SupportTicketService supportTicketService;

	@Autowired
	UserService userService;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam("id") long id) {
		return Response.status(Status.OK).entity(supportTicketService.findById(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSupportTicket(SupportTicket supportTicket) throws URISyntaxException {
		return Response.status(201).entity(supportTicketService.save(supportTicket)).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSupportTicket(SupportTicket supportTicket) throws URISyntaxException {
		return Response.status(201).entity(supportTicketService.update(supportTicket)).build();
	}

	@GET
	@Path("{userID}/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActiveByUserID(@PathParam("userID") long userID) {
		return Response.status(Status.OK)
				.entity(supportTicketService.findByUserAndStatus(userService.readById(userID), SupportTicketStatus.OPEN))
				.build();
	}

	@GET
	@Path("{userID}/closed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInActiveByUserID(@PathParam("userID") long userID) {
		return Response.status(Status.OK).entity(
				supportTicketService.findByUserAndStatus(userService.readById(userID), SupportTicketStatus.CLOSED))
				.build();
	}

	@PUT
	@Path("/close/{ticketid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response closeTicket(@PathParam("ticketid") long ticketid) {
		return Response.status(Status.OK).entity(supportTicketService.close(ticketid)).build();
	}

	@PUT
	@Path("/reopen/{ticketid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response reopenTicket(@PathParam("ticketid") long ticketid) {
		return Response.status(Status.OK).entity(supportTicketService.reopen(ticketid)).build();
	}

	@PUT
	@Path("/resolve/{ticketid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response resolveTicket(@PathParam("ticketid") long ticketid) {
		return Response.status(Status.OK).entity(supportTicketService.resolve(ticketid)).build();
	}
	
	@GET
	@Path("{userID}/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllByUserID(@PathParam("userID") long userID) {
		return Response.status(Status.OK)
				.entity(supportTicketService.findByUser(userService.readById(userID)))
				.build();
	}

}

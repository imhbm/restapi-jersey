package com.himu.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.himu.rest.dao.ContactsDao;
import com.himu.rest.dao.DaoException;
import com.himu.rest.dao.DaoFactory;
import com.himu.rest.entity.Contact;


@Path("/contacts")
public class ContactResources {
	
	ContactsDao dao;
	public ContactResources() throws DaoException
	{
		dao = DaoFactory.getContactsDao();
	}
	@GET
	@Produces("application/json")
	public Response getAllContacts() throws DaoException
	{
		return Response.ok(dao.findAll()).build();
	}
	
	@Path("/{contact_id}")
	@Produces({"application/json"})
	@GET
	public Response getContact_id(@PathParam("contact_id")Integer id) throws DaoException
 	{
		return Response.ok(dao.findById(id)).build();
	}
	
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response addNewContact(Contact contact) throws DaoException
	{
		contact = dao.addContact(contact);
		return Response.ok(contact).build();
	}
	
	@Path("/{contact_id}")
	@PUT
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response updateContact(@PathParam("contact_id") Integer id,Contact contact) throws DaoException
	{
		contact.setId(id);
		contact = dao.updateContact(contact);
		return Response.ok(contact).build();
	}
	
	@Path("/{contacts_id}")
	@Produces({"application/json"})
	@DELETE
	public Response deleteContact(@PathParam("contacts_id") Integer id) throws DaoException
	{
		dao.deleteContact(id);
		return Response.ok().build();
	}

}

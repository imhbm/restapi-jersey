package com.himu.rest.provider;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.himu.rest.dao.DaoException;

@Provider
public class DaoExceptionMapper  implements ExceptionMapper<DaoException>{

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(DaoException exception) {
		// TODO Auto-generated method stub
		Map<String,String> error = new HashMap<>();
		error.put("message", exception.getMessage());
		error.put("when", new Date().toString());
		return Response.status(500).entity(error.toString()).build();
	}

}

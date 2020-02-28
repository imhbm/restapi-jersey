package com.himu.rest.dao;

public final class DaoFactory {
	public static final String IMPL_TYPE = "jdbc";
	private DaoFactory()
	{
		
	}
	
	public static ContactsDao getContactsDao() throws DaoException
	{
		switch(IMPL_TYPE)
		{
		case "jdbc" :
			return new JdbcContactDao();
		default:
			throw new DaoException("No Suitable implimention available");
		}
	}

}

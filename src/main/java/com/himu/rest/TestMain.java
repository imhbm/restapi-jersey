package com.himu.rest;

import com.himu.rest.dao.ContactsDao;
import com.himu.rest.dao.DaoException;
import com.himu.rest.dao.DaoFactory;

public class TestMain {

	public static void main(String[] args) throws DaoException {
		ContactsDao dao;
		dao = DaoFactory.getContactsDao();
		System.out.println(dao.findAll());
	}
}

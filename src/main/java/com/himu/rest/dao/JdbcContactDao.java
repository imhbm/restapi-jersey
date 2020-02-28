package com.himu.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.himu.rest.entity.Contact;
import com.himu.rest.utility.DbUtil;

public class JdbcContactDao implements ContactsDao {

	@Override
	public Contact addContact(Contact contact) throws DaoException {
		String sql = "insert into contacts (name,gender,email,phone,city,country) values (?,?,?,?,?,?)";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCity());
			stmt.setString(6, contact.getCountry());
			
			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			contact.setId(keys.getInt(1));
			
			return contact;

		} catch (Exception ex) {
			throw new DaoException(ex);
		}
	}

	@Override
	public Contact findById(Integer id) throws DaoException {
		String sql = "select * from contacts where id=?";
		try(Connection conn = DbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);)
		{
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				return toContact(rs);
				
			}
			
			rs.close();
			
		}
		catch(Exception ex)
		{
			throw new DaoException(ex);
		}
		return null;
	}

	private Contact toContact(ResultSet rs) throws SQLException {
		Contact c = new Contact();
		c.setId(rs.getInt("id"));
		c.setCity(rs.getString("city"));
		c.setCountry(rs.getString("country"));
		c.setName(rs.getString("name"));
		c.setGender(rs.getString("gender"));
		c.setEmail(rs.getString("email"));
		c.setPhone(rs.getString("phone"));		
		return c;
	}

	@Override
	public Contact updateContact(Contact contact) throws DaoException {
		String sql = "update contacts set name=?,gender=?,email=?,phone=?,city=?,country=? where id=?";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getGender());
			stmt.setString(3, contact.getEmail());
			stmt.setString(4, contact.getPhone());
			stmt.setString(5, contact.getCity());
			stmt.setString(6, contact.getCountry());
			stmt.setInt(7, contact.getId());

			// get the count for update
			int count = stmt.executeUpdate();

			if (count == 0) {
				throw new DaoException("No record updateded , invalid id supplied id - " + contact.getId());
			}

		} catch (Exception ex) {
			throw new DaoException(ex);
		}
		return contact;
	}

	@Override
	public void deleteContact(Integer id) throws DaoException {
		String sql = "delete from contacts where id = ?";
		try (Connection conn = DbUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new DaoException("No record deleted , invalid id supplied id - " + id);
			}

		} catch (Exception ex) {
			throw new DaoException(ex);
		}
	}

	@Override
	public List<Contact> findAll() throws DaoException{
		// TODO Auto-generated method stub
		String sql="select * from contacts";
		List<Contact> contactList = new ArrayList<Contact>();
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery())
		{
			while(rs.next())
			{
				Contact c = toContact(rs);
				contactList.add(c);
				
			}
			rs.close();
			
			return contactList;
			
		}
		catch(Exception ex)
		{
			throw new DaoException(ex);
		}
		
		
	}

	@Override
	public List<Contact> findByCity(String city) throws DaoException {
		String sql="select * from contacts where city = ?";
		List<Contact> contactList = new ArrayList<Contact>();
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);)
				
		{
			stmt.setString(1, city);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Contact c = toContact(rs);
				contactList.add(c);
				
			}
			
			rs.close();
		}
		catch(Exception ex)
		{
			throw new DaoException();
		}
		return contactList;
	}

	@Override
	public List<Contact> findByCountry(String country) throws DaoException {
		String sql="select * from contacts where country = ?";
		List<Contact> contactList = new ArrayList<Contact>();
		try(
				Connection conn = DbUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);)
				
		{
			stmt.setString(1, country);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Contact c = toContact(rs);
				contactList.add(c);
				
			}
			
			rs.close();
		}
		catch(Exception ex)
		{
			throw new DaoException();
		}
		return contactList;
	}

}

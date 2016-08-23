package com.journaldev.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.jdbc.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	@Autowired
	private DataSource dataSource;

	@Override
	public void create(Customer customer) {
		String queryCustomer = "insert into Customer (id, name) values (?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(queryCustomer, new Object[] { customer.getId(), customer.getName() });
		System.out.println("Inserted into Customer Table Successfully");
	}

	@Override
	public Customer find(int id) {
		String queryCustomer = "select ID, NAME from Customer WHERE ID=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return jdbcTemplate.queryForObject(queryCustomer, new Object[] { id }, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Customer customer = new Customer();
				customer.setId(rs.getInt("ID"));
				customer.setName(rs.getString("NAME"));

				return customer;
			}

		});

	}

	@Override
	public int count() {
		String queryCustomer = "select count(*) from Customer";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return jdbcTemplate.queryForObject(queryCustomer, Integer.class);

	}
}
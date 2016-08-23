package com.journaldev.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.jdbc.model.Address;
import com.journaldev.spring.jdbc.model.Customer;

@Repository
public class AddressDAOImpl implements AddressDAO {
	@Autowired
	private DataSource dataSource;

	@Override
	public void create(Address address) {
		String queryAddress = "insert into Address (id, address,country) values (?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(queryAddress, new Object[] { address.getId(), address.getAddress(), address.getCountry() });
		System.out.println("Inserted into Address Table Successfully");
	}

	@Override
	public Address find(int id) {
		String queryAddress = "select ID, COUNTRY, ADDRESS from ADDRESS WHERE ID=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return jdbcTemplate.queryForObject(queryAddress, new Object[] { id }, new RowMapper<Address>() {
			public Address mapRow(ResultSet rs, int rowNum) throws SQLException {

				Address add = new Address();
				add.setId(rs.getInt("ID"));
				add.setCountry(rs.getString("COUNTRY"));

				return add;
			}

		});

	}

	@Override
	public int count() {
		String queryCustomer = "select count(*) from Address";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return jdbcTemplate.queryForObject(queryCustomer, Integer.class);

	}
}
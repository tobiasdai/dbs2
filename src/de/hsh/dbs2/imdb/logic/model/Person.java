package de.hsh.dbs2.imdb.logic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.hsh.dbs2.imdb.util.DBConnection;

public class Person extends AbstractModel {

	private long id;
	private String name;
	private String sex;
	
	public void insert() {
		Connection conn = null;
		id = getNewId("PERSON_SEQ.nextval");
		String sql = "insert into person values(?, ?, ?)";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.setString(2, name);
			stmt.setString(3, sex);
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}

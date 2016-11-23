package de.hsh.dbs2.imdb.logic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.hsh.dbs2.imdb.util.DBConnection;

public class Movie extends AbstractModel {

	private long id;
	private String title;
	private int year;
	private String type;
	
	public void insert() {
		Connection conn = null;
		id = getNewId("MOVIE_SEQ.nextval");
		String sql = "insert into movie values(?, ?, ?, ?)";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.setString(2, title);
			stmt.setInt(3, year);
			stmt.setString(4, type);
			stmt.executeUpdate();
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", type=" + type + "]";
	}
	
}

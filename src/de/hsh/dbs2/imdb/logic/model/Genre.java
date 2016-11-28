package de.hsh.dbs2.imdb.logic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.hsh.dbs2.imdb.util.DBConnection;

public class Genre extends AbstractModel {

	private long id;
	private String genre;
	
	public void insert() {
		Connection conn = null;
		id = getNewId("GENRE_SEQ.nextval");
		String sql = "insert into genre values(?, ?)";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.setString(2, genre);
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}

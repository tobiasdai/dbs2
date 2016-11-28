package de.hsh.dbs2.imdb.logic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.hsh.dbs2.imdb.util.DBConnection;

public class MovieCharacter extends AbstractModel {

	private long id;
	private String character;
	private String alias;
	private int position;
	private long movieId;
	private long playerId;
	
	public void insert() {
		Connection conn = null;
		id = getNewId("MOVIECHARACTER_SEQ.nextval");
		String sql = "insert into MovieCharacter values(?, ?, ?, ?, ?, ?)";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.setString(2, character);
			stmt.setString(3, alias);
			stmt.setInt(4, position);
			stmt.setLong(5, movieId);
			stmt.setLong(6, playerId);
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
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

}

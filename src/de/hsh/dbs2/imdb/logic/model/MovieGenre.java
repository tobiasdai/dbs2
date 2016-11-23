package de.hsh.dbs2.imdb.logic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import de.hsh.dbs2.imdb.util.DBConnection;

public class MovieGenre {

	private long movieId;
	private long genreId;
	
	public void insert() {
		Connection conn = null;
		String sql = "insert into HasGenre values(?, ?)";
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, movieId);
			stmt.setLong(2, genreId);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public long getGenreId() {
		return genreId;
	}

	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}
	
}

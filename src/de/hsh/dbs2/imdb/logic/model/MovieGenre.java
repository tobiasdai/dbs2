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
			stmt.close();
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof MovieGenre))
			return false;
		if (obj == this)
			return true;
		MovieGenre o = (MovieGenre)obj;
		return this.genreId == o.genreId && this.movieId == o.movieId;
	}

	@Override
	public int hashCode() {
		String s = "" + movieId + "" + genreId;
		return s.hashCode();
	}
	
}

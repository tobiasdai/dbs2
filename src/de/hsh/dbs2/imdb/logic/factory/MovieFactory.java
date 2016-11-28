package de.hsh.dbs2.imdb.logic.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.logic.model.Movie;
import de.hsh.dbs2.imdb.util.DBConnection;

public class MovieFactory {
	
	public static List<Movie> getAllMovie() throws SQLException {
		List<Movie> movieList = new ArrayList<>();
		String sql = "select * from movie";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Movie movie = new Movie();
			movie.setId(rs.getLong("movieid"));
			movie.setTitle(rs.getString("title"));
			movie.setYear(rs.getInt("year"));
			movie.setType(rs.getString("type"));
			movieList.add(movie);
		}
		rs.close();
		stmt.close();
		return movieList;
	}

	public static Movie findById(long id) throws SQLException {
		Movie movie = null;
		String sql = "select * from movie where movieid = ?";

		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		movie = new Movie();
		if (rs.next()) {
			movie.setId(rs.getLong("movieid"));
			movie.setTitle(rs.getString("title"));
			movie.setYear(rs.getInt("year"));
			movie.setType(rs.getString("type"));
		}
		rs.close();
		stmt.close();
		return movie;
	}
	
	public static List<Movie> findByTitle(String title) throws SQLException {
		List<Movie> movieList = new ArrayList<>();
		String sql = "select * from movie where upper(title) like upper(?)";
		
		Connection conn = DBConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + title + "%");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Movie movie = new Movie();
			movie.setId(rs.getLong("movieid"));
			movie.setTitle(rs.getString("title"));
			movie.setYear(rs.getInt("year"));
			movie.setType(rs.getString("type"));
			movieList.add(movie);
		}
		rs.close();
		stmt.close();
		return movieList;
	}
	
	public static Movie add(Movie movie) throws SQLException {
		movie.insert();
		return movie;
	}
	
	public static void update(Movie movie) throws SQLException {
		String sql = "update movie set title=?, year=?, type=? where movieid = ?";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, movie.getTitle());
		stmt.setInt(2, movie.getYear());
		stmt.setString(3, movie.getType());
		stmt.setLong(4, movie.getId());
		
		stmt.executeUpdate();
		stmt.close();
	}
	
	public static void delete(long id) throws SQLException {
		String sql = "delete from movie where movieid = ?";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, id);
		
		stmt.executeUpdate();
		stmt.close();
	}
	
}

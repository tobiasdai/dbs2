package de.hsh.dbs2.imdb.logic.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.util.DBConnection;

public class GenreFactory {

	public static List<String> getGenres() throws SQLException {
		String sql = "select * from genre";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<String> genres = new ArrayList<>();
		while (rs.next()) {
			String genre = rs.getString("genre");
			genres.add(genre);
		}
		return genres;
	}
	
	public static String getGenre(long id) throws SQLException {
		String sql = "select * from genre where genreid = ?";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		
		String genre = "";
		if (rs.next()) {
			genre = rs.getString("genre");
		}
		return genre;
	}
}

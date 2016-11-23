package de.hsh.dbs2.imdb.logic.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.logic.model.MovieGenre;
import de.hsh.dbs2.imdb.util.DBConnection;

public class MovieGenreFactory {

	public static List<MovieGenre> getMovieGenre(long movieId) throws SQLException {
		String sql = "select * from hasgenre where movie = ?";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, movieId);
		
		ResultSet rs = stmt.executeQuery();
		
		List<MovieGenre> movieGenres = new ArrayList<>();
		while (rs.next()) {
			MovieGenre mg = new MovieGenre();
			mg.setMovieId(rs.getLong("movie"));
			mg.setGenreId(rs.getLong("genre"));
			movieGenres.add(mg);
		}
		return movieGenres;
	}
	
}

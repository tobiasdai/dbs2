package de.hsh.dbs2.imdb.logic.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.logic.model.MovieCharacter;
import de.hsh.dbs2.imdb.util.DBConnection;

public class CharacterFactory {

	public static List<MovieCharacter> findByMovieId(long movieId) throws SQLException {
		String sql = "select * from moviecharacter where movieid = ?";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, movieId);
		
		ResultSet rs = stmt.executeQuery();
		List<MovieCharacter> characters = new ArrayList<>();
		while (rs.next()) {
			MovieCharacter character = new MovieCharacter();
			character.setId(rs.getLong("movcharid"));
			character.setCharacter(rs.getString("character"));
			character.setAlias(rs.getString("alias"));
			character.setPosition(rs.getInt("position"));
			character.setMovieId(rs.getLong("movieid"));
			character.setPlayerId(rs.getLong("personid"));
			characters.add(character);
		}
		rs.close();
		stmt.close();
		return characters;
	}
	
	public static void delete(long movieId) throws SQLException {
		String sql = "delete from moviecharacter where movieid = ?";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, movieId);
		
		stmt.executeUpdate();
		stmt.close();
	}
	
	public static void add(MovieCharacter character) {
		character.insert();
	}
	
}

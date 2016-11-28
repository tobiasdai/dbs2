package de.hsh.dbs2.imdb.logic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hsh.dbs2.imdb.util.DBConnection;

public abstract class AbstractModel {

	protected long getNewId(String seq) {
		Connection conn = null;
		String sql = "select "+ seq + " from dual";
		long id = 0;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getLong(1);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
}

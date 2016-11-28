package de.hsh.dbs2.imdb.logic.factory;

import de.hsh.dbs2.imdb.logic.model.Person;
import de.hsh.dbs2.imdb.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by dais on 2016-11-23.
 */
public class PersonFactory{
	
    public static ArrayList<Person> findByName(String name) throws SQLException {
        ArrayList<Person> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM PERSON WHERE UPPER(NAME) LIKE UPPER(?)";
        
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,"%"+name+"%");
        ResultSet rs = stmt.executeQuery();
        Person person;
        if(rs.next()){
            person = new Person();
            person.setId(rs.getLong("PERSONID"));
            person.setName(rs.getString("NAME"));
            person.setSex(rs.getString("SEX"));
            arrayList.add(person);
        }
        rs.close();
		stmt.close();
        return arrayList;
    }
    
    public static Person findById(long id) throws SQLException {
    	String sql = "select * from person where personid = ?";
		Connection conn = DBConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, id);
		
		ResultSet rs = stmt.executeQuery();
		Person person = new Person();
		if (rs.next()) {
			person.setId(rs.getLong("personid"));
			person.setName(rs.getString("name"));
			person.setSex(rs.getString("sex"));
		}
		rs.close();
		stmt.close();
    	return person;
	}
}

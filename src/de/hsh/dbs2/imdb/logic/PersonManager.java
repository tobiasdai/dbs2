package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.logic.factory.PersonFactory;
import de.hsh.dbs2.imdb.logic.model.Person;

public class PersonManager {

	/**
	 * Liefert eine Liste aller Personen, deren Name den Suchstring enthaelt.
	 * @param text Suchstring
	 * @return Liste mit passenden Personennamen, die in der Datenbank eingetragen sind.
	 * @throws Exception
	 */
	public List<String> getPersonList(String text) throws Exception {
		ArrayList<String> arrayName = new ArrayList<String>();
		ArrayList<Person> arrayPerson = PersonFactory.findByName(text);
		for(Person p :arrayPerson){
			arrayName.add(p.getName());
		}
		return arrayName;
	}

}

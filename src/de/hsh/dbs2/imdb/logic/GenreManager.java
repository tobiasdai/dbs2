package de.hsh.dbs2.imdb.logic;

import java.util.Collections;
import java.util.List;

import de.hsh.dbs2.imdb.logic.factory.GenreFactory;

public class GenreManager {

	/**
	 * Ermittelt eine vollstaendige Liste aller in der Datenbank abgelegten Genres
	 * Die Genres werden alphabetisch sortiert zurueckgeliefert.
	 * @return Alle Genre-Namen als String-Liste
	 * @throws Exception
	 */
	public List<String> getGenres() throws Exception {
		List<String> genres = GenreFactory.getGenres();
		Collections.sort(genres);
		return genres;
	}

}

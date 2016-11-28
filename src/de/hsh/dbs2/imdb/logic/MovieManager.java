package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.hsh.dbs2.imdb.logic.dto.CharacterDTO;
import de.hsh.dbs2.imdb.logic.dto.MovieDTO;
import de.hsh.dbs2.imdb.logic.factory.CharacterFactory;
import de.hsh.dbs2.imdb.logic.factory.GenreFactory;
import de.hsh.dbs2.imdb.logic.factory.MovieFactory;
import de.hsh.dbs2.imdb.logic.factory.MovieGenreFactory;
import de.hsh.dbs2.imdb.logic.factory.PersonFactory;
import de.hsh.dbs2.imdb.logic.model.Genre;
import de.hsh.dbs2.imdb.logic.model.Movie;
import de.hsh.dbs2.imdb.logic.model.MovieCharacter;
import de.hsh.dbs2.imdb.logic.model.MovieGenre;
import de.hsh.dbs2.imdb.logic.model.Person;
import de.hsh.dbs2.imdb.util.DBConnection;

public class MovieManager {

	/**
	 * Ermittelt alle Filme, deren Filmtitel den Suchstring enthaelt.
	 * Wenn der String leer ist, sollen alle Filme zurueckgegeben werden.
	 * Der Suchstring soll ohne Ruecksicht auf Gross/Kleinschreibung verarbeitet werden.
	 * @param search Suchstring. 
	 * @return Liste aller passenden Filme als MovieDTO
	 * @throws Exception
	 */
	public List<MovieDTO> getMovieList(String search) throws Exception {
		List<MovieDTO> movieList = new ArrayList<>();
		List<Movie> movies;
		if (search.equals("")) {
			movies = MovieFactory.getAllMovie();
		} else {
			movies = MovieFactory.findByTitle(search);
		}
		
		for (Movie movie : movies) {
			MovieDTO movieDTO = getMovie(movie.getId());
			movieList.add(movieDTO);
		}
		
		return movieList;
	}

	/**
	 * Speichert die uebergebene Version des Films neu in der Datenbank oder aktualisiert den
	 * existierenden Film.
	 * Dazu werden die Daten des Films selbst (Titel, Jahr, Typ) beruecksichtigt,
	 * aber auch alle Genres, die dem Film zugeordnet sind und die Liste der Charaktere
	 * auf den neuen Stand gebracht.
	 * @param movie Film-Objekt mit Genres und Charakteren.
	 * @throws Exception
	 */
	public void insertUpdateMovie(MovieDTO movieDTO) throws Exception {
		Movie movie = new Movie();
		movie.setTitle(movieDTO.getTitle());
		movie.setYear(movieDTO.getYear());
		movie.setType(movieDTO.getType());
		
		if (movieDTO.getId() == null) {
			movie = MovieFactory.add(movie);
		} else {
			movie.setId(movieDTO.getId());
			MovieFactory.update(movie);
		}
		
		MovieGenreFactory.delete(movie.getId());
		Set<String> genres = movieDTO.getGenres();
		for (String s : genres) {
			Genre genre = GenreFactory.find(s);
			MovieGenre newMg = new MovieGenre();
			newMg.setMovieId(movie.getId());
			newMg.setGenreId(genre.getId());
			MovieGenreFactory.add(newMg);
		}
		
		CharacterFactory.delete(movie.getId());
		List<CharacterDTO> characterDTOs = movieDTO.getCharacters();
		for (CharacterDTO cDto : characterDTOs) {
			MovieCharacter character = new MovieCharacter();
			character.setCharacter(cDto.getCharacter());
			character.setAlias(cDto.getAlias());
			character.setPlayerId(PersonFactory.findByName(cDto.getPlayer()).get(0).getId());
			character.setMovieId(movie.getId());
			CharacterFactory.add(character);
		}
		
		DBConnection.getConnection().commit();
	}

	/**
	 * Loescht einen Film aus der Datenbank. Es werden auch alle abhaengigen Objekte geloescht,
	 * d.h. alle Charaktere und alle Genre-Zuordnungen.
	 * @param movie
	 * @throws Exception
	 */
	public void deleteMovie(long movieId) throws Exception {
		MovieGenreFactory.delete(movieId);
		CharacterFactory.delete(movieId);
		MovieFactory.delete(movieId);
		DBConnection.getConnection().commit();
	}

	public MovieDTO getMovie(long movieId) throws Exception {
		Movie movie = MovieFactory.findById(movieId);
		
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setId(movie.getId());
		movieDTO.setTitle(movie.getTitle());
		movieDTO.setType(movie.getType());
		movieDTO.setYear(movie.getYear());
		
		List<MovieGenre> mgs = MovieGenreFactory.getMovieGenre(movie.getId());
		for (MovieGenre mg : mgs) {
			String genre = GenreFactory.getGenre(mg.getGenreId());
			movieDTO.addGenre(genre);
		}
		
		List<MovieCharacter> characters = CharacterFactory.findByMovieId(movie.getId());
		for (MovieCharacter character : characters) {
			CharacterDTO characterDTO = new CharacterDTO();
			characterDTO.setAlias(character.getAlias());
			characterDTO.setCharacter(character.getCharacter());
			
			Person person = PersonFactory.findById(character.getPlayerId());
			characterDTO.setPlayer(person.getName());
			
			movieDTO.addCharacter(characterDTO);
		}
		return movieDTO;
	}

}

package co.edu.unbosque.ColPlusSolution.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.ColPlusSolution.model.Movie;
import co.edu.unbosque.ColPlusSolution.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRep;

	public MovieService() {
		// TODO Auto-generated constructor stub
	}

	public int create(Movie newMovie) {
		Integer id = newMovie.getMovieId();
		String title = newMovie.getTitle();
		List<String> genre = newMovie.getGenre();
		Integer year = newMovie.getYear();

		if (movieRep.existsById(id)) {
			return 1;
		} else {
			newMovie.setMovieId(id);
			newMovie.setTitle(title);
			newMovie.setGenre(genre);
			newMovie.setYear(year);
			movieRep.save(newMovie);
			return 0;
		}
	}

	public long count() {
		return movieRep.count();
	}

	public List<Movie> getAll() {
		return (List<Movie>) movieRep.findAll();
	}

	public int deleteById(Integer id) {
		Optional<Movie> found = movieRep.findById(id);
		if (found.isPresent()) {
			movieRep.delete(found.get());
			return 0;
		} else {
			return 1;
		}
	}

	public List<Movie> saveFromDat(InputStream file) {
		List<Movie> movieList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
			String line;

			while ((line = reader.readLine()) != null) {
				System.out.println("LEYENDO");
				String[] data = line.split("::");

				if (data.length != 3) {
					System.err.println("Invalid line format: " + line);
					continue;
				}

				Movie movie = new Movie();

				int movieId = Integer.parseInt(data[0].trim());
				movie.setMovieId(movieId);

				String titleYear = data[1].trim();
				int yearIndex = titleYear.lastIndexOf(" (");
				if (yearIndex != -1 && titleYear.endsWith(")")) {
					String title = titleYear.substring(0, yearIndex).trim();
					movie.setTitle(title);

					String yearStr = titleYear.substring(yearIndex + 2, titleYear.length() - 1).trim();
					int year = Integer.parseInt(yearStr);
					movie.setYear(year);
				} else {
					System.err.println("Invalid title/year format: " + titleYear);
					continue;
				}

				List<String> genres = Arrays.asList(data[2].trim().split("\\|"));
				movie.setGenre(genres);

				if (!movieRep.existsById(movieId)) {
					movieRep.save(movie);
					movieList.add(movie);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieList;
	}

	public int updateById(Integer id, Movie newMovie) {
		Optional<Movie> found = movieRep.findById(id);
		if (found.isPresent()) {
			Movie temp = found.get();
			temp.setTitle(newMovie.getTitle());
			temp.setGenre(newMovie.getGenre());
			temp.setYear(newMovie.getYear());
			movieRep.save(temp);
			return 0;
		}
		return 1;
	}

	public Movie getById(Integer id) {
		Optional<Movie> found = movieRep.findById(id);
		return found.orElse(null);
	}

}

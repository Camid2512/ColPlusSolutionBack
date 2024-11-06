package co.edu.unbosque.ColPlusSolution.service;

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

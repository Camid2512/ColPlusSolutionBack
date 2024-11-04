package co.edu.unbosque.ColPlusSolution.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.ColPlusSolution.Model.Movie;
import co.edu.unbosque.ColPlusSolution.Repository.MovieRepository;

@Service
public class MovieService implements CRUDOperation<Movie> {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public int create(Movie data) {
        movieRepository.save(data);
        return 1;
    }

    @Override
    public List<Movie> getAll() {
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public int deleteById(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateById(Long id, Movie newData) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            Movie updatedMovie = movie.get();
            updatedMovie.setTitle(newData.getTitle());
            updatedMovie.setGenre(newData.getGenre());
            updatedMovie.setYear(newData.getYear());
            movieRepository.save(updatedMovie);
            return 1;
        }
        return 0;
    }

    @Override
    public long count() {
        return movieRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return movieRepository.existsById(id);
    }
}

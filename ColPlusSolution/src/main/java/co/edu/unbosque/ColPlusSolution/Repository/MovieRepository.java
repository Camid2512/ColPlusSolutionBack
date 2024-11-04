package co.edu.unbosque.ColPlusSolution.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.ColPlusSolution.Model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);
    
    Optional<Movie> findByDirector(String director);
    
    Optional<Movie> findByGenre(String genre);
}

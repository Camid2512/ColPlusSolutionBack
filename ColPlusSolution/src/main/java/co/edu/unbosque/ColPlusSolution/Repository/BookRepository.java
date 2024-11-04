package co.edu.unbosque.ColPlusSolution.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.ColPlusSolution.Model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    
    Optional<Book> findByAuthor(String author);
    
    Optional<Book> findByIsbn(String isbn);
}

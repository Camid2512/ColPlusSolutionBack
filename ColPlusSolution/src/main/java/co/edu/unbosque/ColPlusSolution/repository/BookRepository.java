package co.edu.unbosque.ColPlusSolution.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.ColPlusSolution.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

}

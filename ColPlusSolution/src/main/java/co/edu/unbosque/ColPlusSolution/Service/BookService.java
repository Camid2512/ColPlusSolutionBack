package co.edu.unbosque.ColPlusSolution.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unbosque.ColPlusSolution.Model.Book;
import co.edu.unbosque.ColPlusSolution.Repository.BookRepository;

@Service
public class BookService implements CRUDOperation<Book> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public int create(Book data) {
        bookRepository.save(data);
        return 1;
    }

    @Override
    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public int deleteById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateById(Long id, Book newData) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book updatedBook = book.get();
            updatedBook.setTitle(newData.getTitle());
            updatedBook.setAuthors(newData.getAuthors());
            updatedBook.setPublisher(newData.getPublisher());
            updatedBook.setLanguageCode(newData.getLanguageCode());
            bookRepository.save(updatedBook);
            return 1;
        }
        return 0;
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public boolean exist(Long id) {
        return bookRepository.existsById(id);
    }
}

package co.edu.unbosque.ColPlusSolution.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.ColPlusSolution.model.Book;
import co.edu.unbosque.ColPlusSolution.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRep;

    public BookService() {
        // Constructor por defecto
    }

    public int create(Book newBook) {
        Integer bookId = newBook.getBookId();
        String title = newBook.getTitle();
        List<String> authors = newBook.getAuthors();
        Float averageRating = newBook.getAverageRating();
        String isbn = newBook.getIsbn();
        String isbn13 = newBook.getIsbn13();
        String languageCode = newBook.getLanguageCode();
        Integer numberOfPages = newBook.getNumberOfPages();
        Integer ratingCount = newBook.getRatingCount();
        Integer textReviewCount = newBook.getTextReviewCount();
        Date publicationDate = newBook.getPublicationDate();
        String publisher = newBook.getPublisher();
        String extraField = newBook.getExtraField();

        if (bookRep.existsById(bookId)) {
            return 1; // Retorna 1 si el libro ya existe
        } else {
            newBook.setBookId(bookId);
            newBook.setTitle(title);
            newBook.setAuthors(authors);
            newBook.setAverageRating(averageRating);
            newBook.setIsbn(isbn);
            newBook.setIsbn13(isbn13);
            newBook.setLanguageCode(languageCode);
            newBook.setNumberOfPages(numberOfPages);
            newBook.setRatingCount(ratingCount);
            newBook.setTextReviewCount(textReviewCount);
            newBook.setPublicationDate(publicationDate);
            newBook.setPublisher(publisher);
            newBook.setExtraField(extraField);
            bookRep.save(newBook);
            return 0; // Retorna 0 si el libro es guardado exitosamente
        }
    }

    public long count() {
        return bookRep.count();
    }

    public List<Book> getAll() {
        return (List<Book>) bookRep.findAll();
    }

    public int deleteById(Integer bookId) {
        Optional<Book> found = bookRep.findById(bookId);
        if (found.isPresent()) {
            bookRep.delete(found.get());
            return 0; // Retorna 0 si se elimina exitosamente
        } else {
            return 1; // Retorna 1 si no se encuentra el libro
        }
    }

    public int updateById(Integer bookId, Book updatedBook) {
        Optional<Book> found = bookRep.findById(bookId);
        if (found.isPresent()) {
            Book existingBook = found.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthors(updatedBook.getAuthors());
            existingBook.setAverageRating(updatedBook.getAverageRating());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setIsbn13(updatedBook.getIsbn13());
            existingBook.setLanguageCode(updatedBook.getLanguageCode());
            existingBook.setNumberOfPages(updatedBook.getNumberOfPages());
            existingBook.setRatingCount(updatedBook.getRatingCount());
            existingBook.setTextReviewCount(updatedBook.getTextReviewCount());
            existingBook.setPublicationDate(updatedBook.getPublicationDate());
            existingBook.setPublisher(updatedBook.getPublisher());
            existingBook.setExtraField(updatedBook.getExtraField());
            bookRep.save(existingBook);
            return 0; // Retorna 0 si se actualiza exitosamente
        } else {
            return 1; // Retorna 1 si no se encuentra el libro
        }
    }

    public Book getById(Integer bookId) {
        Optional<Book> found = bookRep.findById(bookId);
        return found.orElse(null); // Retorna el libro si existe, o null si no existe
    }
}

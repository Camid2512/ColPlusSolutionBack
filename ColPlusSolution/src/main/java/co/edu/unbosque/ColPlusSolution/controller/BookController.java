package co.edu.unbosque.ColPlusSolution.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.ColPlusSolution.model.Book;
import co.edu.unbosque.ColPlusSolution.service.BookService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bokServ;

	public BookController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(path = "/create")
	ResponseEntity<String> create(@RequestParam Integer bookId, @RequestParam String title,
			@RequestParam List<String> authors, @RequestParam Float avgRating, @RequestParam String isbn,
			@RequestParam String isbn13, @RequestParam String languageCode, @RequestParam Integer ratingCount,
			@RequestParam Integer textReviewCount, @RequestParam Date publicationDate, @RequestParam String publisher,
			@RequestParam String extraField) {
		Book newBook = new Book(bookId, title, authors, avgRating, isbn, isbn13, languageCode, bookId, ratingCount,
				textReviewCount, publicationDate, publisher, extraField);
		System.out.println("CREANDO EMPLEADO");
		int status = bokServ.create(newBook);

		if (status == 0) {
			return new ResponseEntity<String>("Book added successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error adding a book, check id", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJson(@RequestBody Book newBook) {

		System.out.println("CREANDO EMPLEADO");
		int status = bokServ.create(newBook);

		if (status == 0) {
			return new ResponseEntity<String>("Book added successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error adding a book, check id", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping(path = "/createwithjson", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Book>> createWithJson(@RequestParam("file") MultipartFile file) {
		try {
			List<Book> books = bokServ.saveFromJson(file.getInputStream());
			return new ResponseEntity<>(books, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Book>> getAll() {

		try {

			List<Book> books = bokServ.getAll();
			if (books.isEmpty()) {
				return new ResponseEntity<>(books, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(books, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countAll() {

		Long count = bokServ.count();
		if (count == 0) {

			return new ResponseEntity<>(count, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
		}

	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Book> getById(@PathVariable Integer id) {
		Book found = bokServ.getById(id);
		if (found != null) {
			return new ResponseEntity<>(found, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/update")
	ResponseEntity<String> updateNew(@RequestParam Integer bookId, @RequestParam String title,
			@RequestParam List<String> authors, @RequestParam Float avgRating, @RequestParam String isbn,
			@RequestParam String isbn13, @RequestParam String languageCode, @RequestParam Integer ratingCount,
			@RequestParam Integer textReviewCount, @RequestParam Date publicationDate, @RequestParam String publisher,
			@RequestParam String extraField) {
		Book newBook = new Book(bookId, title, authors, avgRating, isbn, isbn13, languageCode, bookId, ratingCount,
				textReviewCount, publicationDate, publisher, extraField);
		int status = bokServ.updateById(bookId, newBook);

		if (status == 0) {
			return new ResponseEntity<>("Book data updated succesfully", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error on update", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	ResponseEntity<String> deleteById(@PathVariable Integer id) {
		int status = bokServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<>("Book deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}

}

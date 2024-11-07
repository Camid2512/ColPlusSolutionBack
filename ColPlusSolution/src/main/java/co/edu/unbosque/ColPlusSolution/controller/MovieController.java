package co.edu.unbosque.ColPlusSolution.controller;

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

import co.edu.unbosque.ColPlusSolution.model.Movie;
import co.edu.unbosque.ColPlusSolution.service.MovieService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin(origins = { "http://localhost:8083", "http://localhost:8082", "*" })
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movServ;

	public MovieController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewWithJson(@RequestBody Movie newMovie) {

		System.out.println("CREANDO EMPLEADO");
		int status = movServ.create(newMovie);

		if (status == 0) {
			return new ResponseEntity<String>("Movie added successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error adding a movie, check id", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping(path = "/createwithexcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Movie>> createWithExcel(@RequestParam("file") MultipartFile file) {
		try {
			List<Movie> movies = movServ.saveFromDat(file.getInputStream());
			return new ResponseEntity<>(movies, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Movie>> getAll() {

		try {

			List<Movie> movies = movServ.getAll();
			if (movies.isEmpty()) {
				return new ResponseEntity<>(movies, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(movies, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countAll() {

		Long count = movServ.count();
		if (count == 0) {

			return new ResponseEntity<>(count, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
		}

	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Movie> getById(@PathVariable Integer id) {
		Movie found = movServ.getById(id);
		if (found != null) {
			return new ResponseEntity<>(found, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/update")
	ResponseEntity<String> updateNew(@RequestParam Integer movieId, @RequestParam String title,
			@RequestParam Integer year, List<String> genre) {
		Movie newMovie = new Movie(movieId, title, year, genre);
		int status = movServ.updateById(movieId, newMovie);

		if (status == 0) {
			return new ResponseEntity<>("Movie data updated succesfully", HttpStatus.ACCEPTED);
		} else if (status == 1) {
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Error on update", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deletebyid/{id}")
	ResponseEntity<String> deleteById(@PathVariable Integer id) {
		int status = movServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<>("Movie deleted successfully", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Error on delete", HttpStatus.NOT_FOUND);
		}
	}
}
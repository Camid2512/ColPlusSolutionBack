package co.edu.unbosque.ColPlusSolution.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private Integer year;

    @ElementCollection
    @Column(name = "genre")
    private List<String> genre;

    public Movie() {
    	// TODO Auto-generated constructor stub
    }

	public Movie(Integer movieId, String title, Integer year, List<String> genre) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.genre = genre;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

    
}

package co.edu.unbosque.ColPlusSolution.Model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@Column(name = "book_id")
	private Integer bookId;

	@Column(name = "title")
	private String title;

	@ElementCollection
	@Column(name = "authors")
	private List<String> authors;

	@Column(name = "average_rating")
	private Float averageRating;

	@Column(name = "isbn")
	private String isbn;

	@Column(name = "isbn13")
	private String isbn13;

	@Column(name = "language_code")
	private String languageCode;

	@Column(name = "number_of_pages")
	private Integer numberOfPages;

	@Column(name = "rating_count")
	private Integer ratingCount;

	@Column(name = "text_review_count")
	private Integer textReviewCount;

	@Column(name = "publication_date")
	private Date publicationDate;

	@Column(name = "publisher")
	private String publisher;

	@Column(name = "extra_field")
	private String extraField;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(Integer bookId, String title, List<String> authors, Float averageRating, String isbn, String isbn13,
			String languageCode, Integer numberOfPages, Integer ratingCount, Integer textReviewCount,
			Date publicationDate, String publisher, String extraField) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.authors = authors;
		this.averageRating = averageRating;
		this.isbn = isbn;
		this.isbn13 = isbn13;
		this.languageCode = languageCode;
		this.numberOfPages = numberOfPages;
		this.ratingCount = ratingCount;
		this.textReviewCount = textReviewCount;
		this.publicationDate = publicationDate;
		this.publisher = publisher;
		this.extraField = extraField;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Integer getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}

	public Integer getTextReviewCount() {
		return textReviewCount;
	}

	public void setTextReviewCount(Integer textReviewCount) {
		this.textReviewCount = textReviewCount;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getExtraField() {
		return extraField;
	}

	public void setExtraField(String extraField) {
		this.extraField = extraField;
	}

}

package com.springboot.app.filter.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {

	private int bookId;

	private String title;

	private LocalDate publicationDate;

	private String summary;

	private int pages;

	private Author author;

	public Book() {
	}

	public Book(String title, LocalDate publicationDate, String summary, int pages, Author author) {
		this.title = title;
		this.publicationDate = publicationDate;
		this.summary = summary;
		this.pages = pages;
		this.author = author;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public String getSummary() {
		return summary;
	}

	public Author getAuthor() {
		return author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getFormattedPublicationDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		return publicationDate.format(formatter);
	}
}

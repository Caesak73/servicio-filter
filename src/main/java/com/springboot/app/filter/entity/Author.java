package com.springboot.app.filter.entity;

public class Author {

	private int authorId;

	private String name;

	private String biography;

	private String firstSurname;

	public Author() {
	}

	public Author(String name, String biography, String firstSurname) {
		this.name = name;
		this.biography = biography;
		this.firstSurname = firstSurname;
	}
	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getFirstSurname() {
		return firstSurname;
	}
}

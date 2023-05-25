package com.springboot.app.filter;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springboot.app.filter.entity.Book;

@SpringBootApplication
public class ServicioFilterApplication {

	public static void main(String[] args) {

		List<Book> books = loadBooks("book.json");

		String filter = "Harry"; // Cadena de caracteres a filtrar

		Optional<Book> filteredBook = filter(filter, books);
		if (filteredBook.isPresent()) {
			Book book = filteredBook.get();
			System.out.println("Libro encontrado: " + book.getTitle());
			System.out.println("Fecha de publicación: " + book.getFormattedPublicationDate());
			System.out.println(
					"Fecha actual: " + book.getPublicationDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		} else {
			System.out.println("No se encontraron libros que cumplan con los criterios de filtrado.");
		}
	}

	private static List<Book> loadBooks(String filePath) {
		Gson gson = new GsonBuilder().create();
		try (FileReader reader = new FileReader(filePath)) {
			Book[] books = gson.fromJson(reader, Book[].class);
			return Arrays.asList(books);
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	private static Optional<Book> filter(String filter, List<Book> books) {
		List<Book> filteredBooks = new ArrayList<>();

		// Filtrar los libros que no tienen fecha de publicación
		for (Book book : books) {
			if (book.getPublicationDate() == null) {
				System.out.println("Libro sin fecha de publicación: " + book.getTitle());
			} else {
				filteredBooks.add(book);
			}
		}

		// Filtrar los libros que contienen la cadena de caracteres en nombre, resumen o
		// biografía
		List<Book> matchingBooks = new ArrayList<>();
		for (Book book : filteredBooks) {
			if (book.getTitle().contains(filter) || book.getSummary().contains(filter)
					|| book.getAuthor().getBiography().contains(filter)) {
				matchingBooks.add(book);
			}
		}

		// Ordenar por fecha de publicación (de más reciente a más antigua) y biografía
		// más corta
		matchingBooks.sort(Comparator.comparing(Book::getPublicationDate).reversed()
				.thenComparing(Comparator.comparingInt(book -> book.getAuthor().getBiography().length())));

		if (matchingBooks.isEmpty()) {
			return Optional.empty();
		} else {
			Book mostRecentBook = matchingBooks.get(0);
			mostRecentBook.setPublicationDate(LocalDate.now()); // Establecer la fecha actual
			return Optional.of(mostRecentBook);
		}
	}
}
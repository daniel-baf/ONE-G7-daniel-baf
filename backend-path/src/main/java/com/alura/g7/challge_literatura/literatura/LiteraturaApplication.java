package com.alura.g7.challge_literatura.literatura;

import API.Controller.APIController;
import API.Domain.ListBooks;
import API.Domain.SearchParameter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class LiteraturaApplication {

    public static void main(String[] args) {
        APIController controller = new APIController();

        // Listar libros con paginación
        ListBooks allBooks = controller.listBooks(1);
        System.out.println("Total de libros: " + allBooks.getCount());

        // Buscar libros por ID
        ListBooks specificBooks = controller.searchBooksById(11, 21);
        specificBooks.getBooks().forEach(book -> System.out.println("Título: " + book.getTitle()));

        // Búsqueda personalizada
        // Example 1: Filter by author and language
        Map<SearchParameter, String> filters = new HashMap<>();
        filters.put(SearchParameter.AUTHOR, "jane austen");
        filters.put(SearchParameter.LANGUAGE, "en");

        ListBooks filteredBooks = controller.filterBooks(filters, "downloads", 1);
        filteredBooks.getBooks().forEach(book -> System.out.println("Título: " + book.getTitle()));

        // Example 2: Filter by topic only
        filters.clear();
        filters.put(SearchParameter.TOPIC, "romance");

        filteredBooks = controller.filterBooks(filters, null, 1);
        filteredBooks.getBooks().forEach(book -> System.out.println("Título: " + book.getTitle()));
    }


}

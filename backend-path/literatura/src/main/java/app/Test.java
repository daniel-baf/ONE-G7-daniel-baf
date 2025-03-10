package app;

import java.util.HashMap;
import java.util.Map;

import app.API.Controller.APIController.APIController;
import app.API.Domain.Book;
import app.API.Domain.ListBooks;
import app.API.Domain.SearchParameter;

public class Test {

    APIController controller = new APIController();


    public void all() {
        // Listar libros con paginación
        ListBooks allBooks = controller.listBooks(1);
        System.out.println("Total de libros: " + allBooks.getCount());
        System.out.println("---------------");
    }

    public void byId(int... id) {
        // Buscar libros por ID
        ListBooks specificBooks = controller.searchBooksById(id);
        specificBooks.getBooks().forEach(book -> System.out.println("title: " + book.getTitle()));
        System.out.println("---------------");
    }

    public void withParameters() {
        try {
            // Búsqueda personalizada
            Map<SearchParameter, String> filters = new HashMap<>();
            filters.put(SearchParameter.LANGUAGE, "en");
            ListBooks filteredBooks = controller.filterBooks(filters, "downloads", 1);
            filteredBooks.getBooks().forEach(book -> System.out.println("Título: " + book.getTitle()));
            System.out.println("---------------");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void find() {
        Book book = this.controller.find(81);
        System.out.println(book.getTitle());
    }

    public void test() {
       this.all();
//        this.byId(1, 20);
//        this.withParameters(filters, "AUTOR Y LENGUAJE");
//        this.find();
    }

    public static void main(String[] args) {
        Test literaturaApplication = new Test();
        literaturaApplication.test();
//        SpringApplication.run(LiteraturaApplication.class, args);
    }


}

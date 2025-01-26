package app.Menu.BooksCollection;

import app.Menu.MenuHandler;
import app.Utils.ScreenUtils;
import app.API.Controller.APIController.APIController;
import app.API.Domain.ListBooks;
import app.API.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BooksDisplayer is a concrete implementation of the MenuHandler interface
 * that handles the display of all books without applying filters.
 * It forms part of a chain of responsibility for menu option handling,
 * delegating unhandled options to the next handler in the chain.
 * <p>
 * This class processes menu option "2" to display all books and forwards
 * any other option to the next handler if available.
 * <p>
 * Responsibilities:
 * - Handles the "display all books" menu option.
 * - Integrates with other menu handlers as part of a chain.
 * <p>
 * Key Methods:
 * - handleOption(int option): Handles an option if it's "2" or delegates it
 * to the next handler in the chain.
 * - setNext(MenuHandler next): Sets the next handler in the chain.
 */

@Component
public class BooksDisplayerHandler implements MenuHandler {
    private MenuHandler next;
    private final APIController controller;
    private final BookService bookService;

    @Autowired
    public BooksDisplayerHandler(APIController controller, BookService bookService) {
        this.controller = controller;
        this.bookService = bookService;
    }

    @Override
    public void handleOption(int option) {
        if (option == 2) {
            displayAllBooks();
        } else if (next != null) {
            next.handleOption(option);
        }
    }

    @Override
    public void setNext(MenuHandler next) {
        this.next = next;
    }

    private void displayAllBooks() {
        ScreenUtils.cleanScreen("... llamando a la API para mostrar todos los libros ...");
        ListBooks books = this.controller.listBooks(1);
        ScreenUtils.cleanScreen("... mostrando todos los libros ...");
        books.getBooks().forEach(System.out::println);
        // save books
        this.bookService.saveBooksToDatabase(books);

    }
}

package Menu.BooksCollection;

import Menu.MenuHandler;

/**
 * BooksDisplayer is a concrete implementation of the MenuHandler interface
 * that handles the display of all books without applying filters.
 * It forms part of a chain of responsibility for menu option handling,
 * delegating unhandled options to the next handler in the chain.
 *
 * This class processes menu option "2" to display all books and forwards
 * any other option to the next handler if available.
 *
 * Responsibilities:
 * - Handles the "display all books" menu option.
 * - Integrates with other menu handlers as part of a chain.
 *
 * Key Methods:
 * - handleOption(int option): Handles an option if it's "2" or delegates it
 *   to the next handler in the chain.
 * - setNext(MenuHandler next): Sets the next handler in the chain.
 */
public class BooksDisplayerHandler implements MenuHandler {
    private MenuHandler next;

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
        System.out.println("Mostrando todos los libros...");
        // TODO dipslay all books
    }
}

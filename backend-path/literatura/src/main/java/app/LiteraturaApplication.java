package app;

import app.API.Controller.APIController.APIController;
import app.API.Controller.BookController;
import app.API.Controller.PersonController;
import app.API.Service.BookService;
import app.Menu.BooksCollection.BooksDisplayerHandler;
import app.Menu.BooksCollection.FilterHandler;
import app.Menu.Generics.ExitHandler;
import app.Utils.ScreenUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Scanner;

@Getter
enum MenuOptions {
    FILTER(1), DISPLAY_BOOKS(2), EXIT(0);

    private final int value;

    MenuOptions(int value) {
        this.value = value;
    }

}

@SpringBootApplication(scanBasePackages = "app.API")
public class LiteraturaApplication implements CommandLineRunner {

    private final Scanner scanner = new Scanner(System.in);
    private final APIController controller;
    private final BookController bookController;
    private final PersonController personController;

    @Autowired
    public LiteraturaApplication(BookController bookController, PersonController personController) {
        this.controller = new APIController();
        this.personController = personController;
        this.bookController = bookController;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args); // Properly bootstrap Spring Boot
    }

    @Override
    public void run(String... args) {
        displayMenu();
    }

    private void displayMenu() {
        ScreenUtils.cleanScreen(null);
        BookService bookService = new BookService(this.bookController, this.personController);
        FilterHandler filterHandler = new FilterHandler(new HashMap<>(), scanner, this.controller, bookService);
        BooksDisplayerHandler booksDisplayerHandler = new BooksDisplayerHandler(this.controller, bookService);
        ExitHandler exitHandler = new ExitHandler();

        filterHandler.setNext(booksDisplayerHandler);
        booksDisplayerHandler.setNext(exitHandler);

        int option;
        do {
            option = getMenuOption();
            filterHandler.handleOption(option);
        } while (option != MenuOptions.EXIT.getValue());
        System.exit(0);
    }

    private int getMenuOption() {
        System.out.println("\n--- Menú de opciones ---");
        System.out.println(MenuOptions.FILTER.getValue() + ". Buscar libro con filtros");
        System.out.println(MenuOptions.DISPLAY_BOOKS.getValue() + ". Mostrar todos los libros");
        System.out.println(MenuOptions.EXIT.getValue() + ". Salir");
        System.out.print("Selecciona una opción (0-2): ");
        int option = getNextValidInt();
        ScreenUtils.cleanScreen(null);
        return option;
    }

    private int getNextValidInt() {
        while (!scanner.hasNextInt()) {
            System.out.flush();
            System.out.println("Por favor, ingresa un número válido.");
            scanner.next(); // Clear invalid input
        }
        int validNumber = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        return validNumber;
    }
}
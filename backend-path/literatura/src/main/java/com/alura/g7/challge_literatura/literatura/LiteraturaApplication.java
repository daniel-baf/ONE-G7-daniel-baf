package com.alura.g7.challge_literatura.literatura;

import Menu.BooksCollection.BooksDisplayerHandler;
import Menu.BooksCollection.FilterHandler;
import Menu.Generics.ExitHandler;
import Utils.ScreenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import API.Controller.APIController.APIController;

import java.util.HashMap;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.alura.g7.challge_literatura.literatura", "API.Controller"})
public class LiteraturaApplication implements CommandLineRunner {

    private static final int MENU_OPTION_EXIT = 0;
    private static final int MENU_OPTION_FILTER = 1;
    private static final int MENU_OPTION_DISPLAY_BOOKS = 2;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired // Let Spring inject this bean
    private APIController controller;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args); // Properly bootstrap Spring Boot
    }

    @Override
    public void run(String... args) {
        displayMenu();
    }

    private void displayMenu() {
        ScreenUtils.cleanScreen(null);
        FilterHandler filterHandler = new FilterHandler(new HashMap<>(), scanner, this.controller);
        BooksDisplayerHandler booksDisplayerHandler = new BooksDisplayerHandler(this.controller);
        ExitHandler exitHandler = new ExitHandler();

        filterHandler.setNext(booksDisplayerHandler);
        booksDisplayerHandler.setNext(exitHandler);

        int option;
        do {
            option = getMenuOption();
            filterHandler.handleOption(option);
        } while (option != MENU_OPTION_EXIT);
    }

    private int getMenuOption() {
        System.out.println("\n--- Menú de opciones ---");
        System.out.println(MENU_OPTION_FILTER + ". Buscar libro con filtros");
        System.out.println(MENU_OPTION_DISPLAY_BOOKS + ". Mostrar todos los libros");
        System.out.println(MENU_OPTION_EXIT + ". Salir");
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

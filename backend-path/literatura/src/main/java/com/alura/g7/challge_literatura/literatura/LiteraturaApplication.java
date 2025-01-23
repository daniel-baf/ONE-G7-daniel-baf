package com.alura.g7.challge_literatura.literatura;

import Menu.BooksCollection.BooksDisplayerHandler;
import Menu.BooksCollection.FilterHandler;
import Menu.Generics.ExitHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mostrarMenu();
    }

    private void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> filtros = new HashMap<>();  // Mapa para almacenar los filtros

        // Crear los manejadores
        FilterHandler filtroHandler = new FilterHandler(filtros, scanner);
        BooksDisplayerHandler mostrarLibrosHandler = new BooksDisplayerHandler();
        ExitHandler salirHandler = new ExitHandler();

        // Enlazar los manejadores con Chain of Responsibility
        filtroHandler.setNext(mostrarLibrosHandler);
        mostrarLibrosHandler.setNext(salirHandler);

        int opcion = 0;

        do {
            System.out.println("\n--- Menú de opciones ---");
            System.out.println("1. Buscar libro con filtros");
            System.out.println("2. Mostrar todos los libros");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción (1-0): ");

            // Validar la entrada
            while (!scanner.hasNextInt()) {
                System.out.flush();
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next(); // Limpiar el buffer de entrada
            }
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer de la nueva línea

            // Procesar la opción seleccionada
            filtroHandler.handleOption(opcion);
        } while (true);
    }
}

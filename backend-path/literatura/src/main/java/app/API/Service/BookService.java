package app.API.Service;


import app.API.Controller.BookController;
import app.API.Controller.PersonController;
import app.API.Domain.Book;
import app.API.Domain.ListBooks;
import app.API.Domain.Person;
import app.Utils.ScreenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookController bookService;
    private final PersonController personService;

    @Autowired
    public BookService(BookController bookService, PersonController personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @Transactional
    public void saveBooksToDatabase(ListBooks filteredBooks) {
        try {
            ScreenUtils.cleanScreen("GUARDANDO DATOS");
            // Obtener la lista de libros filtrados
            List<Book> books = filteredBooks.getBooks();

            // Guardar todos los libros en la base de datos
            for (Book book : books) {
                for (Person author : book.getAuthors()) {
                    if (author.getId() == 0) {  // New author, save it
                        personService.savePerson(author);
                    } else {  // Existing author, merge it
                        personService.mergePerson(author);
                    }
                }

                bookService.saveBook(book); // Using BookService instead of BookController
            }

            logger.info("LIBROS Y AUTORES INSERTADOS CORRECTAMENTE");

        } catch (Exception e) {
            logger.error("Error al guardar los libros en la base de datos: " + e.getMessage());
            throw e; // Ensure the transaction is rolled back in case of an error
        }
    }
}


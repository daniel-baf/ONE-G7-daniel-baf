package API.Service;

import API.Domain.ListBooks;
import API.Domain.Book;
import API.Domain.Person;
import API.Repository.BookRepository;
import API.Repository.PersonRepository;
import Utils.ScreenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void save(ListBooks filteredBooks) {

        try {
            // Obtener la lista de libros filtrados
            List<Book> books = filteredBooks.getBooks();

            // Guardar todos los libros en la base de datos
            books.forEach(book -> {
                for (Person author : book.getAuthors()) {
                    if (!personRepository.existsById(author.getId())) {
                        personRepository.save(author);
                    }
                }
                if (!bookRepository.existsById(book.getId())) {
                    bookRepository.save(book);
                }
            });
            ScreenUtils.cleanScreen("LIBROS Y AUTORES INSERTADOS CORRECTAMENTE");

        } catch (Exception e) {
            System.out.println("Error al guardar los libros en la base de datos " + e.getMessage());
            throw e; // Ensure the transaction is rolled back in case of an error
        }

    }
}

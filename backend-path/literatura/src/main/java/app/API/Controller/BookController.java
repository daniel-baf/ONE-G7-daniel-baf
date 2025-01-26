package app.API.Controller;

import app.API.Domain.Book;
import app.API.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void saveBook(Book book) {
        this.bookRepository.save(book);
    }

    public Book getBookById(Integer id) {
        return this.bookRepository.findById(id).orElse(null);
    }
}

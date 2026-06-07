package ru.itis.bookcatalog.books.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.bookcatalog.books.controller.api.BookApi;
import ru.itis.bookcatalog.books.dto.BookDto;
import ru.itis.bookcatalog.books.dto.NewBookDto;
import ru.itis.bookcatalog.books.service.BookService;

import java.util.List;

@RestController
public class BookController implements BookApi {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<BookDto> getBooks() {
        return bookService.getBooks();
    }

    @Override
    public BookDto addBook(NewBookDto newBook) {
        return bookService.addBook(newBook);
    }
}

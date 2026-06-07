package ru.itis.bookcatalog.books.service;

import org.springframework.stereotype.Service;
import ru.itis.bookcatalog.books.dto.BookDto;
import ru.itis.bookcatalog.books.dto.NewBookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final List<BookDto> books = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public BookService() {
        books.add(new BookDto(idGenerator.getAndIncrement(), "The Hobbit", "J. R. R. Tolkien", 1937));
        books.add(new BookDto(idGenerator.getAndIncrement(), "Clean Code", "Robert Martin", 2008));
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public BookDto addBook(NewBookDto newBook) {
        BookDto book = new BookDto(
                idGenerator.getAndIncrement(),
                newBook.getTitle(),
                newBook.getAuthor(),
                newBook.getYear()
        );

        books.add(book);
        return book;
    }
}

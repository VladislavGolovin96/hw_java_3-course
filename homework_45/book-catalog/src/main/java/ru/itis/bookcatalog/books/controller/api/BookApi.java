package ru.itis.bookcatalog.books.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.bookcatalog.books.dto.BookDto;
import ru.itis.bookcatalog.books.dto.NewBookDto;

import java.util.List;

@Tag(name = "Books", description = "Методы для работы с каталогом книг")
@RequestMapping("/api/v1/books")
public interface BookApi {

    @Operation(summary = "Получение списка книг", description = "Возвращает все книги из каталога")
    @ApiResponse(responseCode = "200", description = "Список книг успешно получен")
    @GetMapping
    List<BookDto> getBooks();

    @Operation(summary = "Добавление книги", description = "Добавляет новую книгу в каталог")
    @ApiResponse(responseCode = "201", description = "Книга успешно добавлена")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    BookDto addBook(@RequestBody NewBookDto newBook);
}

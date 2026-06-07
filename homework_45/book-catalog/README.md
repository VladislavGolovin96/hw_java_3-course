# Book Catalog

Простой проект на Spring Boot для домашнего задания.

Проект хранит список книг в памяти приложения и предоставляет два метода:

- `GET /api/v1/books` - получить список книг
- `POST /api/v1/books` - добавить новую книгу

## Запуск

```bash
mvn spring-boot:run
```

После запуска приложение будет доступно по адресу:

```text
http://localhost:8080
```

## Документация

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

## Пример POST-запроса

```json
{
  "title": "Clean Code",
  "author": "Robert Martin",
  "year": 2008
}
```

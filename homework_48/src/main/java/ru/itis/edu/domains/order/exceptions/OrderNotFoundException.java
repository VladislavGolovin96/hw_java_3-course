package ru.itis.edu.domains.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.edu.handler.exceptions.NotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends NotFoundException {

    public OrderNotFoundException(Long id) {
        super("Order with id <" + id + "> not found.");
    }
}

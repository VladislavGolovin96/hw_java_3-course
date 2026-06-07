package ru.itis.cars.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.itis.cars.dto.CarDto;
import ru.itis.cars.dto.NewCarDto;
import ru.itis.cars.dto.PageDto;

@RequestMapping("/api/v1/cars")
public interface CarApi {

    @Operation(summary = "List of cars", description = "Get all cars")
    @ApiResponse(description = "Page with cars", responseCode = "200")
    @GetMapping
    PageDto<CarDto> getAll(
            @Parameter(description = "Page number") @RequestParam("page") int page,
            @Parameter(description = "Page size") @RequestParam("size") int size,
            @Parameter(description = "Sorted by: ") @RequestParam("sort") String sort
    );

    @Operation(summary = "Add car", description = "Add car to data base")
    @ApiResponse(description = "Info about added car", responseCode = "201")
    @PostMapping
    CarDto createCar(@RequestBody NewCarDto newCarDto);

    @Operation(summary = "Car info", description = "Get car info")
    @ApiResponses(value = {
            @ApiResponse(description = "Car info by ID", responseCode = "200"),
            @ApiResponse(description = "Car is not found", responseCode = "404")

    })
    @GetMapping("/{car-id}")
    CarDto getCarById(@Parameter(description = "Car ID") @PathVariable("car-id") Long carId);
}

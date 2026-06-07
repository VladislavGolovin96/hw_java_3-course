package ru.itis.cars.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.itis.cars.entity.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarDto {

    @Schema(description = "Car ID", example = "1")
    private Long id;
    @Schema(description = "Car model", example = "Mercedes-Benz")
    private String model;
    @Schema(description = "Car color", example = "Black")
    private String color;
    @Schema(description = "Car price", example = "2000000")
    private Double price;

    private CarDto() {
    }
    public CarDto(Long id, String model, Double price, String color) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.color = color;
    }

    public static CarDto from(Car car) {
        return new CarDto(car.getId(), car.getModel(), car.getPrice(), car.getColor());
    }

    public static List<CarDto> from(List<Car> cars) {
        return cars
                .stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Double getPrice() {
        return price;
    }
}

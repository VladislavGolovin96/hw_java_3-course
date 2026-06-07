package ru.itis.user.dto;

public class UserDto {
    private final String id;
    private final String email;
    private final String name;

    public UserDto(String id, String name, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

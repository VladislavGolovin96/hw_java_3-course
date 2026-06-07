package ru.itis.edu.domains.user.service;

import org.junit.jupiter.api.*;
import ru.itis.edu.domains.user.dto.NewUserDto;
import ru.itis.edu.domains.user.dto.UserDto;
import ru.itis.edu.domains.user.entity.User;
import ru.itis.edu.domains.user.exceptions.UserNotFoundException;
import ru.itis.edu.domains.user.mapper.UserMapper;
import ru.itis.edu.domains.user.repository.UserRepository;

import java.lang.reflect.Proxy;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceTest {

    @Nested
    @DisplayName("createUser() works ...")
    class CreateUser {

        @Test
        public void create_user_returns_created_user_with_id() {
            // новый пользователь, который идет в метод createUser
            NewUserDto newUser = new NewUserDto("Marsel", "Sidikov", "Qwerty007", "marsel@mail.com", 32);

            // Объект, который мы ожидаем получить после работы репозитория
            User savedUser = new User(1L, "Marsel",
                    "Sidikov",
                    "Qwerty007",
                    "marsel@mail.com",
                    32);

            // Объект, который мы получим после работы метода createUser
            UserDto expectedUser = new UserDto(
                    1L, "Marsel", "Sidikov", "Qwerty007", "marsel@mail.com", 32);

            AtomicReference<User> userForSave = new AtomicReference<>();
            UserRepository userRepository = repository(savedUser, Optional.empty(), userForSave);
            UserService userService = new UserService(userRepository, new UserMapper());

            UserDto actualUser = userService.createUser(newUser);

            assertAll(
                    () -> assertEquals(newUser.getFirstName(), userForSave.get().getFirstName()),
                    () -> assertEquals(newUser.getLastName(), userForSave.get().getLastName()),
                    () -> assertEquals(newUser.getPassword(), userForSave.get().getPassword()),
                    () -> assertEquals(newUser.getEmail(), userForSave.get().getEmail()),
                    () -> assertEquals(newUser.getAge(), userForSave.get().getAge()),
                    () -> assertUserDtoEquals(expectedUser, actualUser)
            );
        }
    }

    @Nested
    @DisplayName("getUser() works ...")
    class GetUser {

        @Test
        public void get_user_returns_correct_user() {
            // подготовка данных

            // ожидаемый пользователь из репозитория
            User userFromRepository = new User(1L, "Marsel",
                    "Sidikov",
                    "qwerty007",
                    "marsel@mail.com",
                    32);

            // ожидаемый пользователь из сервиса
            UserDto expectedUser = new UserDto(
                    1L, "Marsel", "Sidikov", "qwerty007", "marsel@mail.com", 32);

            // когда репозиторию посылают 1L, он должен вернуть заранее подготовленный объект
            UserRepository userRepository = repository(null, Optional.of(userFromRepository), new AtomicReference<>());
            UserService userService = new UserService(userRepository, new UserMapper());

            UserDto actualUser = userService.getUser(1L);

            assertUserDtoEquals(expectedUser, actualUser);
        }

        @Test
        public void get_user_throws_exception_when_wrong_user_id() {
            UserRepository userRepository = repository(null, Optional.empty(), new AtomicReference<>());
            UserService userService = new UserService(userRepository, new UserMapper());

            assertThrows(UserNotFoundException.class, () -> userService.getUser(1L));
        }
    }

    private static UserRepository repository(User savedUser,
                                             Optional<User> foundUser,
                                             AtomicReference<User> userForSave) {
        return (UserRepository) Proxy.newProxyInstance(
                UserRepository.class.getClassLoader(),
                new Class[]{UserRepository.class},
                (proxy, method, args) -> switch (method.getName()) {
                    case "save" -> {
                        userForSave.set((User) args[0]);
                        yield savedUser;
                    }
                    case "findById" -> foundUser;
                    case "toString" -> "UserRepository test fake";
                    default -> throw new UnsupportedOperationException(method.getName());
                });
    }

    private static void assertUserDtoEquals(UserDto expectedUser, UserDto actualUser) {
        assertAll(
                () -> assertEquals(expectedUser.getId(), actualUser.getId()),
                () -> assertEquals(expectedUser.getFirstName(), actualUser.getFirstName()),
                () -> assertEquals(expectedUser.getLastName(), actualUser.getLastName()),
                () -> assertEquals(expectedUser.getPassword(), actualUser.getPassword()),
                () -> assertEquals(expectedUser.getEmail(), actualUser.getEmail()),
                () -> assertEquals(expectedUser.getAge(), actualUser.getAge())
        );
    }
}

package ru.itis.edu.domains.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import ru.itis.edu.domains.user.dto.NewUserDto;
import ru.itis.edu.domains.user.dto.UserDto;
import ru.itis.edu.domains.user.exceptions.UserNotFoundException;
import ru.itis.edu.domains.user.service.UserService;
import ru.itis.edu.handler.GlobalExceptionHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserControllerTest {

    MockMvc mockMvc;

    StubUserService userService;

    @BeforeEach
    void setUp() {
        userService = new StubUserService();
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();

        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(validator)
                .build();
    }

    @Nested
    @DisplayName("GET /api/v1/users/{user-id} works ...")
    class GetUser {

        @Test
        public void get_user_returns_correct_user() throws Exception {
            UserDto expectedUser = new UserDto(
                    1L, "Marsel", "Sidikov", "Qwerty007", "marsel@mail.com", 32);

            userService.userToReturn = expectedUser;

            mockMvc.perform(get("/api/v1/users/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Marsel"))
                    .andExpect(jsonPath("$.lastName").value("Sidikov"))
                    .andExpect(jsonPath("$.password").value("Qwerty007"))
                    .andExpect(jsonPath("$.email").value("marsel@mail.com"))
                    .andExpect(jsonPath("$.age").value(32));
        }

        @Test
        public void get_user_returns_not_found_when_wrong_id() throws Exception {
            userService.exceptionToThrow = new UserNotFoundException(1L);

            mockMvc.perform(get("/api/v1/users/1"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value("User with id <1> not found."))
                    .andExpect(jsonPath("$.timestamp").exists());
        }
    }

    @Nested
    @DisplayName("POST /api/v1/users works ...")
    class CreateUser {

        @Test
        public void create_user_returns_created_user() throws Exception {
            UserDto expectedUser = new UserDto(
                    1L, "Marsel", "Sidikov", "Qwerty007", "marsel@mail.com", 32);

            userService.userToReturn = expectedUser;

            mockMvc.perform(post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "firstName": "Marsel",
                                      "lastName": "Sidikov",
                                      "password": "Qwerty007",
                                      "email": "marsel@mail.com",
                                      "age": 32
                                    }
                                    """))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Marsel"))
                    .andExpect(jsonPath("$.lastName").value("Sidikov"))
                    .andExpect(jsonPath("$.password").value("Qwerty007"))
                    .andExpect(jsonPath("$.email").value("marsel@mail.com"))
                    .andExpect(jsonPath("$.age").value(32));
        }

        @Test
        public void create_user_returns_bad_request_when_request_is_not_valid() throws Exception {
            mockMvc.perform(post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "firstName": "",
                                      "lastName": "Sidikov",
                                      "password": "qwerty007",
                                      "email": "wrong-email",
                                      "age": -1
                                    }
                                    """))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value("Validation errors"))
                    .andExpect(jsonPath("$.errors.firstName").value("EDU: Null value/empty value are not allowed"))
                    .andExpect(jsonPath("$.errors.password").value("Not safe password"))
                    .andExpect(jsonPath("$.errors.email").value("Invalid format of email"))
                    .andExpect(jsonPath("$.errors.age").value("age is lower than 0"))
                    .andExpect(jsonPath("$.timestamp").exists());
        }
    }

    private static class StubUserService extends UserService {

        UserDto userToReturn;

        RuntimeException exceptionToThrow;

        public StubUserService() {
            super(null, null);
        }

        @Override
        public UserDto createUser(NewUserDto newUser) {
            return userToReturn;
        }

        @Override
        public UserDto getUser(Long userId) {
            if (exceptionToThrow != null) {
                throw exceptionToThrow;
            }
            return userToReturn;
        }
    }
}

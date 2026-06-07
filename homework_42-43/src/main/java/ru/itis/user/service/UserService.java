package ru.itis.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.itis.user.dto.NewUserDto;
import ru.itis.user.dto.UserDto;
import ru.itis.user.entity.User;
import ru.itis.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDto createUser(NewUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User saved =  userRepository.save(user);
        return new UserDto(saved.getId().toString(), saved.getName(), saved.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(new UserDto(user.getId().toString(), user.getName(), user.getEmail()));
        }

        return userDtos;
    }

    @Transactional(readOnly = true)
    public UserDto getById(Long id) {
        User findUser = userRepository.findById(id).get();

        return new UserDto(findUser.getId().toString(), findUser.getName(), findUser.getEmail());
    }

    private User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public UserDto updateUser(NewUserDto dto, Long id) {
        User user = getUser(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        return new UserDto(user.getId().toString(), user.getName(), user.getEmail());
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

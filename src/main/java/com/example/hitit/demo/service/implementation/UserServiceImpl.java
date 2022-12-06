package com.example.hitit.demo.service.implementation;

import com.example.hitit.demo.dtos.UserDto;
import com.example.hitit.demo.entity.User;
import com.example.hitit.demo.repository.UserRepository;
import com.example.hitit.demo.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        user.setCreatedAt(LocalDate.now());
        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> modelMapper.map(value, UserDto.class)).orElse(null);
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        Optional<User> user1 = userRepository.findById(id);

        if (user1.isPresent()){
            user1.get().setUserName(user.getUserName());
            user1.get().setEmail(user.getEmail());
            user1.get().setPin(user.getPin());
            user1.get().setBirthday(user.getBirthday());
            user1.get().setPassword(user.getPassword());
            return modelMapper.map(userRepository.save(user1.get()),UserDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Long getUserIdByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()){
            return user.get().getId();
        }
        return 1L;
    }
}

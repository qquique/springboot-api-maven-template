package com.qquique.jsbm.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qquique.jsbm.application.dto.UserDTO;
import com.qquique.jsbm.application.mapper.UserMapper;
import com.qquique.jsbm.domain.entity.User;
import com.qquique.jsbm.infrastructure.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.mapToDomain(userDTO);
        return UserMapper.INSTANCE.mapToDTO(userRepository.save(user));
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::mapToDTO)
                .toList();
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return UserMapper.INSTANCE.mapToDTO(user);
    }

    public boolean deleteById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        } else {
            throw new EntityNotFoundException("user id not found");
        }
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        UserMapper.INSTANCE.updateUserFromDTO(userDTO, user);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.mapToDTO(user);
    }
}

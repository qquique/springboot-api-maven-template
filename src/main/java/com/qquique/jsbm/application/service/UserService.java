package com.qquique.jsbm.application.service;

import com.qquique.jsbm.application.dto.UserDTO;
import com.qquique.jsbm.application.mapper.UserMapper;
import com.qquique.jsbm.domain.entity.User;
import com.qquique.jsbm.infrastructure.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public UserDTO saveUser(UserDTO userDTO) {
        try {
            User user = UserMapper.INSTANCE.mapToDomain(userDTO);
            return UserMapper.INSTANCE.mapToDTO(userRepository.save(user));
        } catch (Exception e) {
            logger.error("error creating user");
            throw e;
        }
    }

    public List<UserDTO> findAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(UserMapper.INSTANCE::mapToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve users", e);
        }
    }

    public UserDTO findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return UserMapper.INSTANCE.mapToDTO(optionalUser.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public boolean deleteById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User updateUser = optionalUser.get();
            UserMapper.INSTANCE.updateUserFromDTO(userDTO, updateUser);
            updateUser = userRepository.save(updateUser);
            return UserMapper.INSTANCE.mapToDTO(updateUser);
        } else {
            logger.error("error updating user");
            throw new RuntimeException("error updating error");
        }
    }
}

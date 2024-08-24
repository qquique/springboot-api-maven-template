package com.qquique.jsbm.infrastructure.repository;

import com.qquique.jsbm.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
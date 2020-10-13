package com.example.restfullapi.repo;

import com.example.restfullapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer>{
}

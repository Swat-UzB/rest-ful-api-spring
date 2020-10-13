package com.example.restfullapi.repo;

import com.example.restfullapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}

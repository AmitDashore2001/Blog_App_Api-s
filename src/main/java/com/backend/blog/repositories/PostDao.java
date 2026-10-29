package com.backend.blog.repositories;

import com.backend.blog.entity.Category;
import com.backend.blog.entity.Post;
import com.backend.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostDao extends JpaRepository<Post,Long> {
        public List<Post> findByUser(User user);
        public List<Post> findByCategory(Category category);
}

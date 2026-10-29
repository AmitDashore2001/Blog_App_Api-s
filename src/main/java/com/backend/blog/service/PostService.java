package com.backend.blog.service;

import com.backend.blog.entity.Post;
import com.backend.blog.payloads.PostDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

    @Service
    public interface PostService{
    public PostDto addPost(PostDto postDto,Long id, Long categoryId);
    public PostDto getPostById(long id);
    public List<PostDto> getAllPost();
    public PostDto updatePost(PostDto postDto, long id);
    public void deletePost(long id);
    public List<PostDto> getPostByUser(long userId);
    public List<PostDto> getPostByCategory(long categoryId);

}

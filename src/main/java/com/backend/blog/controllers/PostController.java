package com.backend.blog.controllers;

import com.backend.blog.payloads.PostDto;
import com.backend.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    public PostService postService;

    @PostMapping("/user/{id}/category/{categoryId}/post")
    public PostDto createPost(@RequestBody PostDto postDto, @PathVariable long id, @PathVariable long categoryId){
        return this.postService.addPost(postDto,id,categoryId);
    }
    @GetMapping("/user/{id}/post")
    public List<PostDto> getAllPostByUser(@PathVariable long id){
        return this.postService.getPostByUser(id);
    }
    @GetMapping("/category/{categoryId}/post")
    public List<PostDto> getPostByCategory(@PathVariable long categoryId){
        return this.postService.getPostByCategory(categoryId);
    }
    @GetMapping("/post/{id}")
    public PostDto findById(@PathVariable long id){
        return this.postService.getPostById(id);
    }
    @GetMapping("/post")
    public List<PostDto> getAllPost(){
        return this.postService.getAllPost();
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id){
        this.postService.deletePost(id);
    }
    @PutMapping("/post/{id}")
    public PostDto updatePost(@RequestBody PostDto postDto, @PathVariable long id){
      return this.postService.updatePost(postDto,id);
    }
}

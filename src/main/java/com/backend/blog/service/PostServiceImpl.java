package com.backend.blog.service;

import com.backend.blog.entity.Category;
import com.backend.blog.entity.Post;
import com.backend.blog.entity.User;
import com.backend.blog.exception.ResourceNotFoundException;
import com.backend.blog.payloads.PostDto;
import com.backend.blog.repositories.CategoryDao;
import com.backend.blog.repositories.PostDao;
import com.backend.blog.repositories.UserDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

     @Autowired
     private PostDao postDao;
     @Autowired
     private UserDao userDao;
     @Autowired
     private CategoryDao categoryDao;
     @Autowired
     private ModelMapper modelMapper;
     public PostDto addPost(PostDto postDto, Long id, Long categoryId) {
     Post post=this.modelMapper.map(postDto, Post.class);
     User user= this.userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("User is not Present"));
     Category category=this.categoryDao.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category is not present"));
     post.setImageName("default.png");
     post.setAddedDate(new Date());
     post.setUser(user);
     post.setCategory(category);
     this.postDao.save(post);
     return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPostById(long id) {
    Post post=this.postDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post is not Found"));
    return this.modelMapper.map(post,PostDto.class);
    }
    @Override
      public List<PostDto> getAllPost()
    {
      List<Post> post= this.postDao.findAll();
      return post.stream().map(posts-> this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
    }
    @Override
      public PostDto updatePost(PostDto postDto, long id) {
      Post post=this.postDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post is not Present"));
      post.setTitle(postDto.getTitle());
      post.setContent(postDto.getContent());
      post.setImageName(postDto.getImageName());

      Post updatePost=this.postDao.save(post);
      return this.modelMapper.map(updatePost,PostDto.class);
      }
    @Override
      public void deletePost(long id) {
      this.postDao.deleteById(id);
    }
    public List<PostDto> getPostByUser(long userId){
    User user= this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not Present"));
    List<Post> posts=this.postDao.findByUser(user);
    List<PostDto> post1=posts.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
     return post1;
    }
    public List<PostDto> getPostByCategory(long cateogryId){
         Category category=this.categoryDao.findById(cateogryId).orElseThrow(()-> new ResourceNotFoundException("Category is not found"));
          List<Post> postDto=this.postDao.findByCategory(category);
         return postDto.stream().map((postDtos-> this.modelMapper.map(postDtos,PostDto.class))).collect(Collectors.toList());
    }
}

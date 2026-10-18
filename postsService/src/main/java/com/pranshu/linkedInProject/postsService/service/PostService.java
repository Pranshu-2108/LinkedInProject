package com.pranshu.linkedInProject.postsService.service;

import com.pranshu.linkedInProject.postsService.dto.PostCreateRequestDto;
import com.pranshu.linkedInProject.postsService.dto.PostDto;
import com.pranshu.linkedInProject.postsService.entity.Post;
import com.pranshu.linkedInProject.postsService.exception.ResourceNotFoundException;
import com.pranshu.linkedInProject.postsService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        log.info("Creating post by user : {}", userId);
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.info("Getting post by id : {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + postId));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostByUserId(Long userId) {
        log.info("Getting all posts by userId : {}", userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList
                .stream()
                .map((post) -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}

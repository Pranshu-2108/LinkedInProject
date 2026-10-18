package com.pranshu.linkedInProject.postsService.controller;

import com.pranshu.linkedInProject.postsService.dto.PostCreateRequestDto;
import com.pranshu.linkedInProject.postsService.dto.PostDto;
import com.pranshu.linkedInProject.postsService.entity.Post;
import com.pranshu.linkedInProject.postsService.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        PostDto postDto = postService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable("postId") Long postId) {
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable("userId") Long userId) {
        List<PostDto> postDtos = postService.getAllPostByUserId(userId);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
}

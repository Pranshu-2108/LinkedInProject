package com.pranshu.linkedInProject.postsService.controller;

import com.pranshu.linkedInProject.postsService.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class PostLikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        postLikeService.like(postId, 1L);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        postLikeService.unlike(postId, 1L);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



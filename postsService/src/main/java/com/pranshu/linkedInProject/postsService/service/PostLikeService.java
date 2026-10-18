package com.pranshu.linkedInProject.postsService.service;


import com.pranshu.linkedInProject.postsService.entity.Post;
import com.pranshu.linkedInProject.postsService.entity.PostLike;
import com.pranshu.linkedInProject.postsService.exception.BadRequestException;
import com.pranshu.linkedInProject.postsService.exception.ResourceNotFoundException;
import com.pranshu.linkedInProject.postsService.repository.PostLikeRepository;
import com.pranshu.linkedInProject.postsService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;


    @Transactional
    public void like(Long postId, Long userId) {
        log.info("User with id : {} is liking post with id={} ", userId, postId);

        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post not found with id : " + postId));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if (hasAlreadyLiked) {
            throw new BadRequestException("You cannot like the post again");
        }

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);

//        TODO : Send notification to owner of the post
    }


    @Transactional
    public void unlike(Long postId, Long userId) {

        log.info("User with id : {} is unliking post with id={}", userId, postId);

        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post not found with id : " + postId));

        boolean hasLikedThePost = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if (!hasLikedThePost) {
            throw new BadRequestException("You cannot unlike the post you haven't liked yet");
        }

        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
    }
}

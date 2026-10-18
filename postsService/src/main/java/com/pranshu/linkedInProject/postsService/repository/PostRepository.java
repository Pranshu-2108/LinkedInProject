package com.pranshu.linkedInProject.postsService.repository;

import com.pranshu.linkedInProject.postsService.dto.PostDto;
import com.pranshu.linkedInProject.postsService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUserId(Long userId);
}

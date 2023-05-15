package com.jscode.projectclasssmartsangmin.service;

import com.jscode.projectclasssmartsangmin.domain.posts.Posts;
import com.jscode.projectclasssmartsangmin.domain.posts.PostsRepository;
import com.jscode.projectclasssmartsangmin.dto.PostsSaveRequestDto;
import com.jscode.projectclasssmartsangmin.exception.PostsNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public List<Posts> findAll() {
        return postsRepository.findAll();
    }

    @Transactional
    public Posts findById(Long id) {
        return postsRepository.findById(id).orElseThrow(PostsNotFoundException::new);
    }

    @Transactional
    public void update(Long id, String title,  String content) {
        Posts posts = postsRepository.findById(id).orElseThrow(PostsNotFoundException::new);
        posts.update(title, content);
    }
}

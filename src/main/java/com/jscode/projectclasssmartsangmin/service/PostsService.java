package com.jscode.projectclasssmartsangmin.service;

import com.jscode.projectclasssmartsangmin.domain.posts.PostsRepository;
import com.jscode.projectclasssmartsangmin.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PostsService {
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }
}

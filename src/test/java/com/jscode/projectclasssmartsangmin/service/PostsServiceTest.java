package com.jscode.projectclasssmartsangmin.service;

import com.jscode.projectclasssmartsangmin.domain.posts.Posts;
import com.jscode.projectclasssmartsangmin.domain.posts.PostsRepository;
import com.jscode.projectclasssmartsangmin.dto.PostsSaveRequestDto;
import com.jscode.projectclasssmartsangmin.exception.PostsNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostsServiceTest {
    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    @Transactional
    void 게시글작성기능() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("제목")
                .content("내용")
                .build();

        // when
        Long savedId = postsService.save(dto);

        // then
        Posts found = postsRepository.findById(savedId).orElseThrow(PostsNotFoundException::new);
        assertThat(found.getTitle()).isEqualTo(dto.getTitle());
        assertThat(found.getContent()).isEqualTo(dto.getContent());
    }
}
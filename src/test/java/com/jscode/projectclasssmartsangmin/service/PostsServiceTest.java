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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @Transactional
    void 아이디로게시물찾기() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("제목")
                .content("내용")
                .build();
        Long savedId = postsService.save(dto);

        // when
        Posts posts = postsService.findById(savedId);

        // then
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
    }

    @Test
    @Transactional
    void 모든게시물찾기() {
        // given
        PostsSaveRequestDto dtoOne = PostsSaveRequestDto.builder()
                .title("0")
                .content("0")
                .build();
        PostsSaveRequestDto dtoTwo = PostsSaveRequestDto.builder()
                .title("1")
                .content("1")
                .build();
        PostsSaveRequestDto dtoThree = PostsSaveRequestDto.builder()
                .title("2")
                .content("2")
                .build();
        Long forOne = postsService.save(dtoOne);
        Long forTwo = postsService.save(dtoTwo);
        Long forThree = postsService.save(dtoThree);

        // when
        List<Posts> all = postsService.findAll();

        // then
        Posts postsOne = all.get(0);
        Posts postsTwo = all.get(1);
        Posts postsThree = all.get(2);

        // then
        assertThat(postsOne.getTitle()).isEqualTo(dtoOne.getTitle());
        assertThat(postsOne.getContent()).isEqualTo(dtoOne.getContent());
        assertThat(postsTwo.getTitle()).isEqualTo(dtoTwo.getTitle());
        assertThat(postsTwo.getContent()).isEqualTo(dtoTwo.getContent());
        assertThat(postsThree.getTitle()).isEqualTo(dtoThree.getTitle());
        assertThat(postsThree.getContent()).isEqualTo(dtoThree.getContent());
    }

    @Test
    @Transactional
    void 게시글수정() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("제목")
                .content("내용")
                .build();
        Long savedId = postsService.save(dto);

        // when
        String newTitle = "변경된 제목";
        String newContent = "변경된 내용";
        postsService.update(savedId, newTitle, newContent);

        // then
        Posts posts = postsService.findById(savedId);
        assertThat(posts.getTitle()).isEqualTo(newTitle);
        assertThat(posts.getContent()).isEqualTo(newContent);
    }

    @Test
    @Transactional
    void 게시글삭제()     {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("제목")
                .content("내용")
                .build();
        Long savedId = postsService.save(dto);

        // when
        postsService.delete(savedId);

        // then
        assertThatThrownBy(() -> postsService.findById(savedId)).isInstanceOf(PostsNotFoundException.class);
    }
}
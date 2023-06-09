package com.jscode.projectclasssmartsangmin.domain.posts;

import com.jscode.projectclasssmartsangmin.exception.PostsNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void 게시글생성및조회() {
        // given
        postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .build());

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        Posts posts = all.get(0);
        assertThat(posts.getTitle()).isEqualTo("제목");
        assertThat(posts.getContent()).isEqualTo("내용");
    }

    @Test
    public void 특정게시글아이디로조회기능() {
        // given
        Posts posts = postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .build());

        // when
        Posts byId = postsRepository.findById(posts.getId()).orElseThrow(PostsNotFoundException::new);

        // then
        assertThat(byId.getTitle()).isEqualTo("제목");
        assertThat(byId.getContent()).isEqualTo("내용");
    }

    @Test
    public void 특정게시글수정기능() {
        // given
        Posts posts = postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .build());

        // when
        Posts found = postsRepository.findById(posts.getId()).orElseThrow(PostsNotFoundException::new);
        found.update("제목아님", "내용아님");

        // then
        Posts updated = postsRepository.findById(posts.getId()).orElseThrow(PostsNotFoundException::new);
        assertThat(updated.getTitle()).isEqualTo("제목아님");
        assertThat(updated.getContent()).isEqualTo("내용아님");
    }

    @Test
    public void 특정게시글삭제기능() {
        // given
        Posts posts = postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .build());

        // when
        postsRepository.delete(posts);

        // then
        assertThatThrownBy(() ->
                postsRepository.findById(posts.getId()).orElseThrow(PostsNotFoundException::new))
                .isInstanceOf(PostsNotFoundException.class);
    }

    @Test
    public void BaseTimeEntity등록() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Posts posts = postsRepository.save(Posts.builder()
                .title("제목")
                .content("내용")
                .build());
        // when
        Posts found = postsRepository.findById(posts.getId()).orElseThrow(PostsNotFoundException::new);

        // then
        Posts target = postsRepository.findById(found.getId()).orElseThrow(PostsNotFoundException::new);
        assertThat(target.getCreatedDate()).isAfter(now);
        assertThat(target.getModifiedDate()).isAfter(now);
    }
}

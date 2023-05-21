package com.jscode.projectclasssmartsangmin.service;

import com.jscode.projectclasssmartsangmin.domain.posts.Posts;
import com.jscode.projectclasssmartsangmin.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/posts")
@AllArgsConstructor
@RestController
public class PostsController {
    private PostsService postsService;

    @PostMapping("/")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }

    @GetMapping("/all")
    public List<Posts> findAll() {
        return postsService.findAll();
    }

    @GetMapping("/{id}")
    public Posts findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody String title, String content) {
        postsService.update(id, title, content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postsService.delete(id);
    }
}

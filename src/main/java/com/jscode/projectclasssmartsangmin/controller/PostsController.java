package com.jscode.projectclasssmartsangmin.service;

import com.jscode.projectclasssmartsangmin.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/posts")
@AllArgsConstructor
@RestController
public class PostsController {
    private PostsService postsService;

    @PostMapping("/")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }
}

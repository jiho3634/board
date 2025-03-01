package com.beyond.board.post.dto;

import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetResDto {
    private Long id;
    private String title;
    private String contents;
    private String author_email;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public PostDetResDto detFromEntity(Post post) {
        return PostDetResDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contents(post.getContents())
                .author_email(post.getAuthor().getEmail())
                .createdTime(post.getCreatedTime())
                .updatedTime(post.getUpdatedTime())
                .build();
    }
}

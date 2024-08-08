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
public class PostListResDto {
    private Long id;
    private String title;
    private String author_email;

    public PostListResDto listFromEntity(Post post) {
        return PostListResDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author_email(post.getAuthor().getEmail())
                .build();
    }

}

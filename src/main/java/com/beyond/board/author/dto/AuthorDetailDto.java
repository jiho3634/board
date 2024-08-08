package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDetailDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private int postCounts;
    private LocalDateTime createdTime;

    public AuthorDetailDto fromEntity(Author author) {
        return AuthorDetailDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .password(author.getPassword())
                .role(author.getRole())
                .postCounts(author.getPosts().size())
                .createdTime(author.getCreatedTime())
                .build();
    }
}

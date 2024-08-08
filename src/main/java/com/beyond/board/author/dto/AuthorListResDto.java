package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorListResDto {
    private Long id;
    private String name;
    private String email;

    public AuthorListResDto fromEntity(Author author) {
        return AuthorListResDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build();
    }
}

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
public class AuthorUpdateReqDto {
    private String name;
    private String password;

    public Author fromDtoToEntity() {
        return Author.builder()
                .name(this.name)
                .password(this.password)
                .build();
    }
}

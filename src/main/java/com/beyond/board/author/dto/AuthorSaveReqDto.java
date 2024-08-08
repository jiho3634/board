package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveReqDto {
    private Role role;
    private String name;
    private String email;
    private String password;

    public Author fromDtoToEntity(String encodedPassword) {
        return Author.builder()
                .password(encodedPassword)
                .name(this.name)
                .email(this.email)
                .role(this.role)
                .posts(new ArrayList<>())
                .build();
    }
}

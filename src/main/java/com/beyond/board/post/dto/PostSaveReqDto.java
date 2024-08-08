package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveReqDto {

    private String title;
    private String contents;
    private String appointment;
    private String appointmentTime;

    public Post toEntity(Author author) {
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .author(author)
                .appointment(this.appointment)
                .appointmentTime(stringToLocalDateTime())
                .build();
    }

    public LocalDateTime stringToLocalDateTime() {
        LocalDateTime tmp = null;
        if (Objects.equals(this.appointment, "Y") && !this.appointmentTime.isEmpty()) {
            tmp = LocalDateTime.parse(this.appointmentTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            if (tmp.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("시간입력이 잘못되었습니다.");
            }
        }
        return tmp;
    }
}

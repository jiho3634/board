package com.beyond.board.post.domain;

import com.beyond.board.author.domain.Author;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.dto.PostUpdateReqDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 3000)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    private String appointment;

    private LocalDateTime appointmentTime;

    public void updatePost(PostUpdateReqDto postUpdateReqDto) {
        this.title = postUpdateReqDto.getTitle();
        this.contents = postUpdateReqDto.getContents();
    }

    public void updateAppointment(String appointment) {
        this.appointment = appointment;
    }
}

package com.beyond.board.post.repository;

import com.beyond.board.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p left join fetch p.author")
    List<Post> findAllFetch();

    @Query("select p from Post p left join p.author")
    List<Post> findAllLeftJoin();

    //  Page<Post> : List<Post> + 해당 요소의 Page 정보
    //  Pageable : PageNumber (몇 번 페이지), Size(페이지마다 폋 페이지씩. default 20), 정렬조건
    Page<Post> findAll(Pageable pageable);

    @Query(value = "select p from Post p left join fetch p.author where p.appointment = 'N'",
            countQuery = "select count(p) from Post p where p.appointment = 'N'")
    Page<Post> findAllPageFetch(Pageable pageable);

    Page<Post> findByAppointment(Pageable pageable, String appointment);
}

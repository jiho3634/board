package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
//  롤백처리를 위해 Transactional 어노테이션 사용. 자동 롤백
@Transactional
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorSaveTest() {
        //  테스트 원리 : save -> 재조회 -> 저장할 때 만든 객체와 비교
        //  준비 (prepare, given)
        Author author = Author.builder().name("kim").email("kim@naver.com").password("12341234").role(Role.ADMIN).build();

        //  실행 (execute, when)
        authorRepository.save(author);
        Author savedAuthor = authorRepository.findByEmail("kim@naver.com").orElse(null);

        //  검증 (then)
        Assertions.assertEquals(author.getEmail(), savedAuthor.getEmail());
    }
}

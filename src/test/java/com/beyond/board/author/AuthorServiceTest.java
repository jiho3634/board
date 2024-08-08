package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@Rollback
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    //  저장 및 detail 조회
    @Test
    public void saveAndFind() {
        AuthorSaveReqDto authorDto = AuthorSaveReqDto.builder().role(Role.ADMIN).name("hongildong").email("hongildong@daum.net").password("12341234").build();
        Author author = authorService.authorCreate(authorDto);
        Assertions.assertEquals(author.getEmail(), authorDto.getEmail());
        Author authorDetail = authorService.authorFindByEmail("hongildong@daum.net");
        Assertions.assertEquals(authorDetail.getEmail(), authorDto.getEmail());
    }

    //  update 검증
    @Test
    public void update() {
        //  객체 저장
        Author author = authorService.authorCreate(AuthorSaveReqDto.builder().role(Role.ADMIN).name("hongildong").email("hongildong@daum.net").password("12341234").build());

        //  수정
        authorService.authorUpdate(author.getId(), AuthorUpdateReqDto.builder().name("hongildong2").password("55555555").build());

        //  재조회 및 검증
        Author authorUpdated = authorService.authorFindByEmail("hongildong@daum.net");
        Assertions.assertEquals("hongildong2", authorUpdated.getName());
        Assertions.assertEquals("55555555", authorUpdated.getPassword());
    }

    //  findAll 검증
    @Test
    public void findAllTest() {
        int size = authorService.authorList().size();
        //  3개 객체 저장
        Author author1 = authorService.authorCreate(AuthorSaveReqDto.builder().role(Role.ADMIN).name("hongildong1").email("hongildong1@daum.net").password("12341234").build());
        Author author2 = authorService.authorCreate(AuthorSaveReqDto.builder().role(Role.ADMIN).name("hongildong2").email("hongildong2@daum.net").password("12341234").build());
        Author author3 = authorService.authorCreate(AuthorSaveReqDto.builder().role(Role.ADMIN).name("hongildong3").email("hongildong3@daum.net").password("12341234").build());

        Assertions.assertEquals(size + 3, authorService.authorList().size());
    }
}

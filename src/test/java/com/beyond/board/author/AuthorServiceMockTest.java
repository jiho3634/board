package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AuthorServiceMockTest {

    @Autowired
    private AuthorService authorService;

    //  가짜 객체를 만드는 작업을 목킹이라고 한다.
    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void findAuthorDetailTest() {
        AuthorSaveReqDto author = AuthorSaveReqDto.builder().name("test").email("test@goole.com").password("12341234").build();
        Author author1 = authorService.authorCreate(author);
//        Author myAuthor = Author.builder().id(1L).name("test").email("test@naver.com").build();
        AuthorDetailDto authorDetailDto = authorService.authorDetail(author1.getId());
//        Author savedAuthor = authorRepository.findById(author1.getId()).orElseThrow(() -> new EntityNotFoundException());
        Mockito.when(authorRepository.findById(author1.getId())).thenReturn(Optional.of(author1));
        Author savedAuthor = authorRepository.findById(author1.getId()).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(authorDetailDto.getEmail(), savedAuthor.getEmail());
    }
}

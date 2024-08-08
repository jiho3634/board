package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

//  저장메소드 위에 @Transactional 추가해줘야함. 매우 귀찮고 에러발생확률이 많아짐.
@Transactional(readOnly = true)
@Service
public class AuthorService {

    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(PasswordEncoder passwordEncoder, AuthorRepository authorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author authorCreate(AuthorSaveReqDto dto) {
        if (authorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }
        if (dto.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }
        Author author = dto.fromDtoToEntity(passwordEncoder.encode(dto.getPassword()));
        author.getPosts().add(Post.builder().author(author).title("가입인사").contents("안녕하세요" + dto.getName() + "입니다.").build());
        return authorRepository.save(author);
    }

    public List<AuthorListResDto> authorList() {
        return authorRepository
                .findAll()
                .stream()
                .map(author -> new AuthorListResDto()
                        .fromEntity(author))
                        .collect(Collectors.toList());
    }

    public AuthorDetailDto authorDetail(Long id) {
        return new AuthorDetailDto()
                .fromEntity(
                    authorRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "member is not found.")));
    }

    public Author authorFindByEmail(String email) {
        return authorRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        "해당 email 사용자는 없습니다."));
    }

    @Transactional
    public void authorDelete(Long id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    public void authorUpdate(Long id, AuthorUpdateReqDto authorUpdateReqDto) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("member is not found"));
        author.updateAuthor(authorUpdateReqDto);
        authorRepository.save(author);
    }
}

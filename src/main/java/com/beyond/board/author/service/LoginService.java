package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//  doLogin 이 자동적으로 이 클래스를 찾아옴
//  Login 할 때 입력한 비밀번호는 자동으로 암호화된다.
//  이 비밀번호와 db 의 암호화된 password 비교하기 위한 코드를 작성해주어야한다.
public class LoginService implements UserDetailsService {

    @Autowired
    private AuthorService authorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorService.authorFindByEmail(username);
        //  public User(String username, String password, Collection<? extends GrantedAuthority > authorities) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + author.getRole()));
        return new User(author.getEmail(), author.getPassword(), authorities);
    }
}

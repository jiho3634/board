package com.beyond.board.author.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession httpSession = request.getSession();

        //  방법 1. authentication 인증객체에서 email 정보 추출
//        httpSession.setAttribute("email", authentication.getName());

        //  방법 2. Security ContextHolder 객체에서 authentication 객체를 꺼낸 뒤 email 정보 추출
        httpSession.setAttribute("email", SecurityContextHolder.getContext().getAuthentication().getName());

        response.sendRedirect("/");
    }
}

//package com.beyond.board.post.service;
//
//import com.beyond.board.post.domain.Post;
//import com.beyond.board.post.repository.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@Component
//public class PostScheduler {
//    private final PostRepository postRepository;
//
//    @Autowired
//    public PostScheduler(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
//    @Scheduled(cron = "0 0/1 * * * *")
//    @Transactional
//    public void postSchedule() {
//        system.out.println("===예약글쓰기 스케쥴러 시작===");
//        page<post> posts = postrepository.findbyappointment(pageable.unpaged(), "y");
//        for (post p : posts) {
//            if (p.getappointmenttime().isbefore(localdatetime.now())) {
//                p.updateappointment("n");
//            }
//        }
//    }
//}

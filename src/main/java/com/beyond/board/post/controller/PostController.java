package com.beyond.board.post.controller;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import com.beyond.board.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/create")
    public String postCreateScreen() {
        return "/post/post_register";
    }

    @PostMapping("/post/create")
    public String postCreate(PostSaveReqDto dto) {
        postService.postCreate(dto);
        return "redirect:/post/list";
    }

    @GetMapping("/post/list")
    public String postList(Model model) {
        model.addAttribute("postList", postService.postList());
        return "/post/post_list";
    }

    @GetMapping("/post/list/page")
    //  Pageable 요청 방식 : localhost:8080/post/list/page?size=10&page=0
    public String postListPage(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("postList", postService.postListPage(pageable));
        return "/post/post_list_page";
    }

    @GetMapping("/post/detail/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.postDetail(id));
        return "/post/post_detail";
    }

    @GetMapping("/post/delete/{id}")
    public String postDelete(@PathVariable Long id) {
        postService.postDelete(id);
        return "redirect:/post/list";
    }

    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Long id,@ModelAttribute PostUpdateReqDto postUpdateReqDto) {
        postService.postUpdate(id, postUpdateReqDto);
        return "redirect:/post/detail/" + id;
    }
}

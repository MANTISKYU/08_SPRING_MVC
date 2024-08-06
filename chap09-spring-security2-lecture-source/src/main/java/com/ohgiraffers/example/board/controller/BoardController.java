package com.ohgiraffers.example.board.controller;

import com.ohgiraffers.example.board.model.dto.CreateBoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @GetMapping("/create")
    public String create() {
        return "board/createBoard";
    }

    @PostMapping("/create")
    public String createPost(@AuthenticationPrincipal UserDetails userDetails, CreateBoardDTO createBoardDTO) {

        String memberId = userDetails.getUsername();

        log.info("로그인한 사용자 ID : {}", memberId);

        log.info("전달받은 BoardDTO : {}" , createBoardDTO);

        return "board/createBoardDTO";

    }
}

package com.ohgiraffers.example.board.service;

import com.ohgiraffers.example.board.model.dto.CreateBoardDTO;
import com.ohgiraffers.example.board.model.entity.Board;
import com.ohgiraffers.example.board.repository.BoardRepository;
import com.ohgiraffers.example.member.model.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void create(CreateBoardDTO boardDTO, Member member) {

        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .member(member)
                .build();

        Board savedBoard = boardRepository.save(board);

        log.info("저장된 게시글 번호 : {}", savedBoard.getBoardId());
    }
}
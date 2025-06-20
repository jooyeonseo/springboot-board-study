package com.study.springstudy.board.controller;

import com.study.springstudy.board.dto.ApiResponseDto;
import com.study.springstudy.board.dto.BoardRequestDto;
import com.study.springstudy.board.dto.BoardResponseDto;
import com.study.springstudy.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/api/post")
    public ResponseEntity<BoardResponseDto> createPost(@RequestBody @Valid BoardRequestDto boardRequestDto) {
        BoardResponseDto responseDto = boardService.createPost(boardRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    // 전체 게시글 조회
    @GetMapping("/api/post")
    public ResponseEntity<List<BoardResponseDto>> getPost() {
        List<BoardResponseDto> responseDtoList = boardService.getAllPosts();
        return ResponseEntity.ok(responseDtoList);
    }

    // 선택 게시글 조회
    @GetMapping("/api/post/{id}")
    public ResponseEntity<BoardResponseDto> getPost(@PathVariable Long id) {
        BoardResponseDto responseDto = boardService.getPostById(id);
        return ResponseEntity.ok(responseDto);
    }

    // 게시글 수정
    @PutMapping("/api/post/{id}")
    public ResponseEntity<BoardResponseDto> updatePost(@PathVariable Long id, @RequestBody @Valid BoardRequestDto boardRequestDto) {
        BoardResponseDto responseDto = boardService.updatePost(id, boardRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deletePost(@PathVariable Long id, @RequestBody @Valid BoardRequestDto boardRequestDto) {
        boardService.deletePost(id,boardRequestDto);
        return ResponseEntity.ok(new ApiResponseDto<>("삭제 완료", 200, null));
    }

}

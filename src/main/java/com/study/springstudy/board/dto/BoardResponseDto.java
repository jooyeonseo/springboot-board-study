package com.study.springstudy.board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {

    private Long id; //게시판 번호
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private String createdDate; // 작성날짜

}

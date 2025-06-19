package com.study.springstudy.board.dto;

import com.study.springstudy.board.entity.BoardEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequestDto {
    //private Long id; //게시판 번호

    @NotBlank
    private String title; // 제목

    @NotBlank
    private String content; // 내용

    @NotBlank
    private String writer; // 작성자

    @NotBlank
    private String password; // 비밀번호

    //private String createdDate; // 작성날짜

    // dto -> entity
    public BoardEntity toEntity() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return BoardEntity.builder()
                //.id(id)
                .title(title)
                .content(content)
                .writer(writer)
                .password(password)
                //.createdDate(createdDate != null? LocalDateTime.parse(createdDate, formatter) : null)
                .build();
    }
}

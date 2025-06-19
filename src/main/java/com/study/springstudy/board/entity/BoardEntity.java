package com.study.springstudy.board.entity;

import com.study.springstudy.board.dto.BoardResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "board")
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //게시판 번호

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private String writer; // 작성자

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(updatable = false)
    private LocalDateTime createdDate; // 작성날짜

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }


    public BoardResponseDto toDto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return BoardResponseDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .createdDate(this.createdDate != null ? this.createdDate.format(formatter) : null)
                .build();
    }


}

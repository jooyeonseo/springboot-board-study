package com.study.springstudy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto<T> {
    private String message; //메세지
    private int status;    // 상태
    private T data;        // 데이터
}

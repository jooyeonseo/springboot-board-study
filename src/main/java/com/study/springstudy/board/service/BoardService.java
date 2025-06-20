package com.study.springstudy.board.service;

import com.study.springstudy.board.dto.BoardRequestDto;
import com.study.springstudy.board.dto.BoardResponseDto;
import com.study.springstudy.board.entity.BoardEntity;
import com.study.springstudy.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 등록
    @Transactional
    public BoardResponseDto createPost(BoardRequestDto boardRequestDto) {
        BoardEntity entity = boardRequestDto.toEntity();
        BoardEntity savedEntity = boardRepository.save(entity);
        return savedEntity.toDto();
    }

    // 게시글 전체 조회
    public List<BoardResponseDto> getAllPosts() {
        List<BoardEntity> entityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));

        return entityList.stream()
                .map(BoardEntity::toDto)
                .collect(Collectors.toList());

    }

    // 선택 게시글 조회
    public BoardResponseDto getPostById(Long id) {
        BoardEntity foundEntity = findPost(id);
        return foundEntity.toDto();
    }

    // 게시글 수정
    @Transactional
    public BoardResponseDto updatePost(Long id, BoardRequestDto boardRequestDto) {

        BoardEntity foundEntity =findPost(id);

        validatePassword(foundEntity, boardRequestDto.getPassword());
        foundEntity.update(boardRequestDto);

        return foundEntity.toDto();
    }


    // 게시글 삭제
    @Transactional
    public void deletePost(Long id, BoardRequestDto boardRequestDto) {
        BoardEntity foundEntity =findPost(id);

        validatePassword(foundEntity, boardRequestDto.getPassword());

        boardRepository.delete(foundEntity);
    }

    private BoardEntity findPost(Long id) {
        return boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    private void validatePassword(BoardEntity boardEntity, String password) {
                if(!boardEntity.getPassword().equals(password)) {
                    throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. ");
                }
    }


}

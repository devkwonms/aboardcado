package com.seo.aboardcado.service;

import com.seo.aboardcado.domain.entity.Board;
import com.seo.aboardcado.domain.repository.BoardRepository;
import com.seo.aboardcado.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class BoardService {

    private BoardRepository boardRepository;

    public Long savePost(BoardDto boardDto) {

        return boardRepository.save(boardDto.toEntity()).getId();
    }

    private BoardDto toDto(Board board){
        return BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
    }

    public List<BoardDto> getBoardList(Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable);
        List<BoardDto> boardList = new ArrayList<>();

        for (Board board : boards){
            boardList.add(this.toDto(board));
        }
        return boardList;
    }

    public BoardDto postDtl(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = this.toDto(board);
        return boardDto;
    }

    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }


}

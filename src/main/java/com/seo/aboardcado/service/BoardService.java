package com.seo.aboardcado.service;

import com.seo.aboardcado.domain.repository.BoardRepository;
import com.seo.aboardcado.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Transactional
    public Long savePost(BoardDto boardDto){

        return boardRepository.save(boardDto.toEntity()).getId();
    }



}

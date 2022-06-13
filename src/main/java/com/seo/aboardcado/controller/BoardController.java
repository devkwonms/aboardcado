package com.seo.aboardcado.controller;

import com.seo.aboardcado.dto.BoardDto;
import com.seo.aboardcado.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller

public class BoardController {

    private BoardService boardService;

    @GetMapping("/post")
    public String post(){
        return "board/post";
    }
    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/post";
    }
}

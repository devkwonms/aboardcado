package com.seo.aboardcado.controller;

import com.seo.aboardcado.domain.entity.Board;
import com.seo.aboardcado.domain.repository.BoardRepository;
import com.seo.aboardcado.dto.BoardDto;
import com.seo.aboardcado.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller

public class BoardController {

    private BoardService boardService;
    private BoardRepository boardRepository;

    @GetMapping("/post")
    public String post(){
        return "board/post";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
        public String detail(@PathVariable("id") Long id, Model model){
            BoardDto boardDto = boardService.postDtl(id);
            model.addAttribute("post", boardDto);
            return "board/detail";
    }

    @GetMapping("/post/edit/{id}")
        public String edit(@PathVariable("id") Long id, Model model){
            BoardDto boardDto = boardService.postDtl(id);
            model.addAttribute("post", boardDto);
            return "board/edit";
    }
    @PutMapping("/post/edit/{id}")
        public String update(BoardDto boardDto){
            boardService.savePost(boardDto);
            return "redirect:/post/{id}";
    }
    @DeleteMapping("/post/{id}")
        public String delete(@PathVariable("id") Long id){
            boardService.deletePost(id);
            return "redirect:/";
    }
    @GetMapping("/")
        public String boardList(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Board> page = boardRepository.findAll(pageable);
        List<BoardDto> boardList = boardService.getBoardList(pageable);

        int nowPage = page.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage-4,1);
        int endPage =   Math.min(nowPage+5, page.getTotalPages());
        int totalPages = page.getTotalPages();

        model.addAttribute("boardList", boardList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages", totalPages);

        return "board/list";


    }

    @GetMapping("/search")
        public String search(@RequestParam(value = "keyword") String keyword,
                             @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        Page<Board> page = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        List<BoardDto> boardList = boardService.searchPosts(keyword, keyword, pageable);

        int nowPage = page.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage-4,1);
        int endPage = Math.min(nowPage+5, page.getTotalPages());
        int totalPages = page.getTotalPages();

        model.addAttribute("Keyword" , keyword);
        model.addAttribute("boardList", boardList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("totalPages", totalPages);

        return "board/list";


    }

}

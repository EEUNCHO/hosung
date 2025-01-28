package com.backend.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.main.model.Menu;
import com.backend.main.model.Board;
import com.backend.main.service.MainService;

@RestController
@RequestMapping("/api")
public class MainController {
  @Autowired
  MainService mainService;

  @GetMapping("/main/getMenu")
  public List<Menu> getMenu() {
    return mainService.getMenu();
  }

  @GetMapping("/main/getBoard")
  public List<Board> getAllList() {
    return mainService.getBoardList();
  }

  @PostMapping("/main/addBoard")
  public int addBoard(@RequestBody Board board) {
    return mainService.addBoard(board);
  }
}
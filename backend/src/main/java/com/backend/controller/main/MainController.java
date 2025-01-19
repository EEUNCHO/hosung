package com.backend.controller.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.model.Menu;
import com.backend.model.Board;
import com.backend.service.MainService;

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
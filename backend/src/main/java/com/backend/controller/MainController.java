package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.model.Board;
import com.backend.service.MainService;

@RestController
@RequestMapping("/api")
public class MainController {
  @Autowired
  MainService mainService;

  @GetMapping("/main/getBoard")
  public List<Board> getAllList() {
    return mainService.getAllList();
  }

  @PostMapping("/main/addBoard")
  public int addBoard(@RequestBody Board board) {
    return mainService.addBoard(board);
  }
}
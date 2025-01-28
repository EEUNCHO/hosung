package com.backend.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.main.mapper.MainMapper;
import com.backend.main.model.Menu;
import com.backend.main.model.Board;

@Service
public class MainService {
  @Autowired
  MainMapper mainMapper;

  public List<Menu> getMenu(){
    return mainMapper.getMenu();
  }
  public List<Board> getBoardList(){
    return mainMapper.getBoardList();
  }
  public int addBoard(Board board){
    return mainMapper.addBoard(board);
  }

}

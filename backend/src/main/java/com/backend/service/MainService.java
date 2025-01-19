package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.mapper.MainMapper;
import com.backend.model.Board;

@Service
public class MainService {
  @Autowired
  MainMapper mainMapper;

  public List<Board> getAllList(){
    return mainMapper.getAllList();
  }
  public int addBoard(Board board){
    return mainMapper.addBoard(board);
  }

}

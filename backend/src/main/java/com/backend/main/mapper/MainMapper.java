package com.backend.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.main.model.Menu;
import com.backend.main.model.Board;

@Mapper
public interface MainMapper {
  public List<Menu> getMenu();
  
  public List<Board> getBoardList();

  public int addBoard(Board board);

}

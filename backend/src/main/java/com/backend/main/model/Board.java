package com.backend.main.model;

import lombok.Data;

@Data
public class Board {
    private Long boardNo;
    private String boardTitle;
    private String boardContentFirst;
    private String boardContent;
}
package com.backend.main.model;

import lombok.Data;

@Data
public class Menu {
    private String menuId;
    private String menuNm;
    private String menuExpln;
    private String menuPath;
    private String upMenuId;
    private long menuSn;
    private long menuLvl;
    private String icon;
    private String menuUseYn;
}

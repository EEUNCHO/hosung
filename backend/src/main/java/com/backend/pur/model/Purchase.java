package com.backend.pur.model;

import lombok.Data;

@Data
public class Purchase {
    private Long rowNum;
    private String purNo;
    private String purDt;
    private String cnptCd;
    private String itemCd;
    private String shipper;
    private Long weight;
    private String unitCd;
    private Long purCnt;
    private Long purUntprc;
    private Long purAmt;
    private String crtDt;
}

package com.backend.pur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pur.mapper.PurMapper;
import com.backend.pur.model.Purchase;

@Service
public class PurService {
  @Autowired
  PurMapper purMapper;

  public List<Purchase> getPurchaseList(String searchDate){
    return purMapper.getPurchaseList(searchDate);
  }

}

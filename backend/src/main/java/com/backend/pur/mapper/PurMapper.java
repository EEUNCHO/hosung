package com.backend.pur.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.backend.pur.model.Purchase;

@Mapper
public interface PurMapper {
  public List<Purchase> getPurchaseList(String searchDate);
}

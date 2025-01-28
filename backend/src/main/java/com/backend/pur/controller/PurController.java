package com.backend.pur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.pur.model.Purchase;
import com.backend.pur.service.PurService;

@RestController
@RequestMapping("/api")
public class PurController {
  @Autowired
  PurService purService;

  @GetMapping("/pur/getPurchase")
  public List<Purchase> getAllList(String searchDate) {
    return purService.getPurchaseList(searchDate);
  }
}
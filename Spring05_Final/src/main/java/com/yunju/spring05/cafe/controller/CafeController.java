package com.yunju.spring05.cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yunju.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService service;
}

package com.gura.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.cafe.dto.CafeDto;
import com.gura.spring.cafe.service.CafeService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeService cafeService;
	
	@RequestMapping("/cafe/list")
	public ModelAndView list(){
		//글목록이 담겨 있는 ModelAndView 객체를 리턴 받는다.
		ModelAndView mView=cafeService.getList();
		//뷰페이지의 정보 설정하고
		mView.setViewName("cafe/list");
		//리턴해준다.
		return mView;
	}
	//새글 입력 폼 요청 처리 
	@RequestMapping("/cafe/private/insertform")
	public String insertForm(){
		return "cafe/private/insertform";
	}
	
	//새글 저장 요청 처리 
	@RequestMapping("/cafe/private/insert")
	public String insert(@ModelAttribute CafeDto dto){
		cafeService.insert(dto);
		return "redirect:/cafe/list.do";
	}
	
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(@RequestParam int num){
	      ModelAndView mView=cafeService.getData(num);
	      mView.setViewName("cafe/detail");
           
	      return mView;
	}
	
}



















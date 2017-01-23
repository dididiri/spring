package com.gura.spring.cafe.service;



import org.springframework.web.servlet.ModelAndView;

import com.gura.spring.cafe.dto.CafeCommentDto;

public interface CafeCommentService {
	public void insert(CafeCommentDto dto);
	public ModelAndView getList(int ref_group);
	public int getSequence();
		
}

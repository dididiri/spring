package com.gura.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gura.spring.shop.dao.ShopDao;

@Component
public class ShopServiceImpl implements ShopService{
    
	//의존 객체 주입
	@Autowired
	private ShopDao shopDao;
	
	//가상의 입금 작업을 하는 메소드
	@Override
	public void deposit(String id, int money) {
		shopDao.deposit(id,money);
		
	}
    //가상의 구입 작업을 하는 메소드
	//입셉션이 발생하지않으면 알아서 커밋해주고
	//입셉셥이 발생하면 알아서 롤빽해줌
	@Transactional
	@Override
	public void buy(String id, int price) {
		//1. 구입 금액의 10% 를 포인트로 환산해서 적립
		int bonusPoint=(int)(price*0.1);
		shopDao.addPoint(id, bonusPoint);
		//2. 계좌 잔액을 줄이고
		shopDao.withDraw(id, price);
		//3. 배송 테이블에 배송 요청과 관련된 작업을한다
		//여기서 특정 입셉션이 됨
		shopDao.deliveryRequest();
        		
	}

}	

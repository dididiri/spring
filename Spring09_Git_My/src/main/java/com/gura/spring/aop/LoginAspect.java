package com.gura.spring.aop;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
@Aspect
@Component
public class LoginAspect {
    
	//return 타입은 모델앤뷰 아니면 String 이당 
	@Around("execution(* com.gura.spring.users.controller.UsersController.auth*(..)) || execution(* com.gura.spring.cafe.controller.CafeController.auth*(..))")
	public Object loginCheck(ProceedingJoinPoint joinPoint)
	        throws Throwable{
		// 컨트롤러에 aop 를 적용했을때 HttpServletRequest 참조값 얻어오기
		ServletRequestAttributes attrs=(ServletRequestAttributes)
		    RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request=attrs.getRequest();
		//세션객체의 참조값 얻어오기
	    HttpSession session=request.getSession();
		if(session.getAttribute("id")==null){
			
            // 원래 요청 uri 정보 얻어오기 (원래 가야되는곳이 어딘지 )
			String uri = request.getRequestURI();
			System.out.println(uri);
			String path = "/users/signin_form.do?uri=" + uri;
			
			//proceed 는 리턴 안하데 무엇가를 리턴해줘야됨!
			//auth 에 리턴타입이 모드 ModelAndView
			ModelAndView mView=new ModelAndView();
			mView.setViewName("redirect:"+path);
			// joinPoint.prceed() 메소드를 호출하지 않고 바로 리턴하면
			// aop 가 적용된 메소드안에 있는 작업이 수행되지 않는다.
			//여기서 리턴해주는 참조값이 aop 가 적용된 메소드에 리턴된다.
			//그러므로 여기서 리턴해주는 data type 과 aop 가 적용된
			//메소드의 리턴 type 은 반드시 같아야한다.
			return mView;
		}else{
			//여기가 수행되면 aop 가 적용된 메소드가 정상 수행된다
			//정상 작업을 한다고 보면됨
			//aop 가 적용된 원래 메소드의 리턴 type 이 일루들어옴
			return joinPoint.proceed();
		}
		
	}
}

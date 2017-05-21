package com.unimer.cotizaciones.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.UserService;


@Component("traceResponseInterceptor")
public class TraceResponseInterceptor  extends HandlerInterceptorAdapter{

	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	
	private static final Log LOG = LogFactory.getLog(TraceResponseInterceptor.class);
	
	
	//PRIMERO
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		request.setAttribute("startTime",System.currentTimeMillis());
		return true;
	}
	
	//SEGUNDO
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception, NullPointerException {
		long startTime = (long) request.getAttribute("startTime");
		String url= request.getRequestURL().toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int idUser=0;
		if(auth!=null && auth.isAuthenticated())
		{
			User user = userService.findByEmail(auth.getName());
			if(user!=null)
			{
				idUser = user.getIdUser();
				traceResponseJpaRepository.save(new TraceResponse(idUser, auth.getDetails().toString(),url,request.getRemoteAddr(), new Date()));
			}
		}
		
		LOG.info("URL to: '" + request.getRequestURL().toString()+"' in '" +(System.currentTimeMillis() - startTime) +"' ms");
	}


	
}

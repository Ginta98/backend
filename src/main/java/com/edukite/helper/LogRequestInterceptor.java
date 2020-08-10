package com.edukite.helper;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogRequestInterceptor implements HandlerInterceptor {
	
	   @Override
	   public boolean preHandle
	      (HttpServletRequest request, HttpServletResponse response, Object handler) 
	      throws Exception {
		   request.setAttribute("START_EXECUTION_TIME", System.currentTimeMillis());
		   return true;
	   }
	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
	      Object handler, ModelAndView modelAndView) throws Exception {

	   }
	   
	   @Override
	   public void afterCompletion
	      (HttpServletRequest request, HttpServletResponse response, Object 
	      handler, Exception exception) throws Exception {
		   long processingTime = System.currentTimeMillis() - (long)request.getAttribute("START_EXECUTION_TIME");
		   Principal principal = request.getUserPrincipal();
		   String userName = "";
		   if (principal != null) {
			   userName = principal.getName();
		   }
		   String uri = request.getRequestURI();
		   if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
			   uri += "?" + request.getQueryString();
		   }
		   log.info(String.format("Request Log|%s|%s|%s", userName, processingTime, uri));
	   }
}

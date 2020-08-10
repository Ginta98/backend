package com.edukite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.edukite.helper.LogRequestInterceptor;

@Configuration
public class LogRequestInterceptorConfig extends WebMvcConfigurerAdapter  {

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new LogRequestInterceptor());
   }
}

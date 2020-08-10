package com.edukite;

import java.util.concurrent.Executor;

import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableIgniteRepositories("com.edukite.repository.cache")
@EnableScheduling
@EnableAsync
public class EdukiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdukiteApplication.class, args);
	}
	
	@Bean
	  public Executor taskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(10);
	    executor.setQueueCapacity(500);
	    executor.setThreadNamePrefix("NpcMobileAsync-");
	    executor.initialize();
	    return executor;
	  }
}

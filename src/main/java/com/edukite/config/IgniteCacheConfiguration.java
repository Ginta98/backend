package com.edukite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class IgniteCacheConfiguration {
	@Value("${ignite.config.path}")
	private String ignitePath;

	@Bean
	public Ignite igniteInstance() {
		if (ignitePath == null || ignitePath.isEmpty()) {
			ignitePath = "apache-ignite/example-ignite.xml";
		}
		return Ignition.start(ignitePath);
	}
}

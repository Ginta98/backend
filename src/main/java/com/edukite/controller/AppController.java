package com.edukite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edukite.helper.ResponseBuilder;
import com.edukite.model.Setting;

@RestController
@RequestMapping("/app")
public class AppController {
	@Value("${app.setting.hotline}")
	private String hotline;

	@Value("${app.setting.force-update-version}")
	private String forceUpdateVersion;

	@Value("${app.setting.email}")
	private String email;

	@Value("${app.setting.version}")
	private String version;

	@GetMapping("/settings")
	public ResponseEntity<ResponseBuilder<Setting>> getSetting() {
		return ResponseEntity.ok(ResponseBuilder.buildResponse(Setting.builder()
				.hotline(hotline)
				.email(email)
				.version(version)
				.forceUpdateVersion(forceUpdateVersion)
				.build()));
	}

}

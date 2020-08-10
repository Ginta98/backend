package com.edukite.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Setting {
	
	private String hotline;
	
	private String version;
	
	private String forceUpdateVersion;
	
	private String email;
}

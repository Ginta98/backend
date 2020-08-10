package com.edukite.helper;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dinhanhthai on 26/07/2020.
 */
public class Helper {
    public static String getUserName() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        OAuth2Authentication principal = (OAuth2Authentication) request.getUserPrincipal();
        return principal.getName();
    }
}

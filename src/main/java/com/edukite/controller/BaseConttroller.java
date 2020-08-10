package com.edukite.controller;

import com.edukite.constant.AppConstants;
import com.edukite.exception.ApiException;
import com.edukite.helper.Helper;
import com.edukite.model.User;
import com.edukite.service.UserService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Year;

public class BaseConttroller {

    protected User _USER = null;
    protected String _USER_NAME = null;


    @Autowired
    UserService userService;

    public Integer validatePage(Integer page) {

        if (page == null || page <= 0) {
            return AppConstants.DEFAULT_PAGE;
        }

        return page;

    }

    public Integer validatePerPage(Integer perPage) {

        if (perPage == null || perPage <= 0) {
            return AppConstants.DEFAULT_PAGE_SIZE;
        } else if (perPage > AppConstants.MAX_PAGE_SIZE) {
            return AppConstants.MAX_PAGE_SIZE;
        }
        return perPage;
    }

    public Integer validateYear(Integer year) {
        if (year >= Year.now().getValue()) {
            return Year.now().getValue();
        }
        return year;
    }

    @ModelAttribute("BeforeRequest")
    public void BeforeRequest(HttpServletRequest request) throws ApiException {
        _USER = getCurrentUser(request);
    }

    public User getCurrentUser(HttpServletRequest request) throws ApiException {
        //String userName = Helper.getUserName();
        String userName = "";
        if (request != null) {
            OAuth2Authentication principal = (OAuth2Authentication) request.getUserPrincipal();
            _USER_NAME = principal.getName();
        } else {
            _USER_NAME = Helper.getUserName();
        }
        _USER = userService.getUser(_USER_NAME);
        System.out.println("___   [USER] Get user info from DB " + _USER_NAME);

        return _USER;
    }
}

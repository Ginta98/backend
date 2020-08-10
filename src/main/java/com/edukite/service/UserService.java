package com.edukite.service;

import com.edukite.model.Student;
import com.edukite.model.User;

/**
 * Created by dinhanhthai on 26/07/2020.
 */
public interface UserService {
    User saveUser(User user);
    User getUser(Integer id);
    User getUser(String userName);
    Student getStudentByCode(String code);
}

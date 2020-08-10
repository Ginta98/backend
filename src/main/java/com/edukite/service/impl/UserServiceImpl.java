package com.edukite.service.impl;

import com.edukite.model.Student;
import com.edukite.model.User;
import com.edukite.repository.StudentResponsitory;
import com.edukite.repository.UserResponsitory;
import com.edukite.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dinhanhthai on 26/07/2020.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserResponsitory userRepository;
    @Autowired
    StudentResponsitory studentResponsitory;

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElse(new User());
    }
    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveUser(User user){
        return new User();
    }

    @Override
    public Student getStudentByCode(String code){
        return studentResponsitory.findByStudentCode(code);
    }

}

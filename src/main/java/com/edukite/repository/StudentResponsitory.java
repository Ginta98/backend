package com.edukite.repository;

import com.edukite.model.Student;
import com.edukite.model.User;
import org.springframework.data.repository.CrudRepository;

public interface StudentResponsitory extends CrudRepository<Student, Integer>  {
    Student findByStudentCode(String code);

}

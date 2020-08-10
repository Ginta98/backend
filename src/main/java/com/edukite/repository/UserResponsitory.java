package com.edukite.repository;

import com.edukite.model.Student;
import org.springframework.data.repository.CrudRepository;
import com.edukite.model.User;
import org.springframework.data.jpa.repository.Query;
public interface UserResponsitory  extends CrudRepository<User, Integer>  {
    User findByUserName(String username);
   /* @Query(value = "from com.edukite.model.User left join  a where a.cpId = ?1 and a.orgId = ?2 and a.callStatus = ?3 and a.orderPhoneNumber = ?4")
    User getRoleByUserName(String username);*/
}

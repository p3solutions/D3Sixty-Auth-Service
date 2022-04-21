package com.example.p3samlfetch.repository;

import com.example.p3samlfetch.entity.user_data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface user_repository extends JpaRepository<user_data, Integer> {


    user_data findByUname(String uname);


}

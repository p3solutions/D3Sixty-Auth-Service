package com.example.p3samlfetch.service;

import com.example.p3samlfetch.entity.user_data;
import com.example.p3samlfetch.repository.user_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class user_service {
    @Autowired
    private user_repository repository;
    public user_data saveuser_data(user_data u)
    {
        return repository.save(u);
    }

    public user_data updateuserdata(user_data u){

        user_data existinguserfname=repository.findByUfname(u.getUfname());
        user_data existingusername=repository.findByUname(u.getUname());
        if(existinguserfname.equals(null) && existingusername.equals(null))
        {
            return null;
        }
        else
        {
            existinguserfname.setUfname(u.getUfname());
            existinguserfname.setUlname(u.getUlname());
            existinguserfname.setUemail(u.getUemail());
            existinguserfname.setUemail(u.getUname());
            existinguserfname.setUgroup(u.getUgroup());
            return repository.save(existinguserfname);
        }
    }

}

package com.example.p3samlfetch.service;

import com.example.p3samlfetch.entity.user_data;
import com.example.p3samlfetch.repository.user_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class user_service {
    @Autowired
    private user_repository repository;
    public user_data saveuser_data(user_data u)
    {
        return repository.save(u);
    }

    public user_data updateuserdata(user_data u){


        user_data existingusername=repository.findByUname(u.getUname());
        if(existingusername.equals(null))
        {
            return null;
        }
        else
        {
            existingusername.setUfname(u.getUfname());
            existingusername.setUlname(u.getUlname());
            existingusername.setUemail(u.getUemail());
            existingusername.setUemail(u.getUname());
            existingusername.setUgroup(u.getUgroup());
            return repository.save(existingusername);
        }
    }

}

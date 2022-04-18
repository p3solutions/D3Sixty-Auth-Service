package com.example.p3samlfetch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")

public class user_data {
    @Id
    @GeneratedValue
    private int seq_num;
    private String uname;
    private String ufname;
    private String ulname;
    private String u_email;
    private String u_role;


    public int getId() {
        return seq_num;
    }

    public void setId(int seq_num) {
        this.seq_num = seq_num;
    }

    public String getUfname() {
        return ufname;
    }

    public void setUfname(String ufname) {
        this.ufname = ufname;
    }

    public String getUlname() {
        return ulname;
    }

    public void setUlname(String ulname) {
        this.ulname = ulname;
    }
    public String getUname(String uname)
    {
        return uname;
    }
    public void setUname(String uname)
    {
        this.uname=uname;
    }
    public String getUemail() {
        return u_email;
    }

    public void setUemail(String u_email) {
        this.u_email = u_email;
    }

    public String getUgroup() {
        return u_role;
    }

    public void setUgroup(String u_role) {
        this.u_role = u_role;
    }
}

package com.example.p3samlfetch;

import com.example.p3samlfetch.entity.user_data;
import com.example.p3samlfetch.repository.user_repository;
import com.example.p3samlfetch.service.user_service;
import org.opensaml.saml2.core.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Controller
public class IndexController
{
    @Value("${security.saml2.Email}")
    String saml_email;
    @Value("${security.saml2.Firstname}")
    String firstname;
    @Value("${security.saml2.Lastname}")
    String lastname;
    @Value("${security.saml2.Userlogin}")
    String user_login;
    @Value("${security.saml2.Group}")
    String group;
    @Value("${decom.entry.url}")
    String decom_entry_url;

    @Autowired
    private user_service s;
    @Autowired

    private user_repository repository;
    @RequestMapping("/")
    public String index(ExpiringUsernameAuthenticationToken userToken, Model model)
    {

        SAMLCredential credential=(SAMLCredential) userToken.getCredentials();
        List<Attribute> attributes=credential.getAttributes();
        System.out.println("userToken.getName()"+userToken.getName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user_data u=new user_data();
        for(Attribute data:attributes)
        {
            System.out.println(data.getName() + "==" +credential.getAttributeAsString(data.getName()));

            if(!StringUtils.isEmpty(data.getName()))
            {

                if(data.getName().trim().equals(saml_email))
                {
                    model.addAttribute("Email",credential.getAttributeAsString(data.getName()));
                    u.setUemail(credential.getAttributeAsString(data.getName()));
                }
                else if(data.getName().trim().equals(firstname))
                {
                    model.addAttribute("Firstname",credential.getAttributeAsString(data.getName()));
                    u.setUfname(credential.getAttributeAsString(data.getName()));
                }

                else if(data.getName().trim().equals(lastname))
                {
                    model.addAttribute("Lastname",credential.getAttributeAsString(data.getName()));
                    u.setUlname(credential.getAttributeAsString(data.getName()));
                }
                else if(data.getName().trim().equals(user_login))
                {
                    model.addAttribute("Username",credential.getAttributeAsString(data.getName()));
                    u.setUname(credential.getAttributeAsString(data.getName()));
                }

                else if(data.getName().trim().equals(group))
                {
                    model.addAttribute("Group",credential.getAttributeAsString(data.getName()));
                    u.setUgroup(credential.getAttributeAsString(data.getName()));
                }


            }

        }
        String decom=decom_entry_url+"Login_1.jsp";
        model.addAttribute("url",decom);


        user_data ufname=repository.findByUfname(u.getUfname());
        user_data uname=repository.findByUname(u.getUname());


        if(Objects.isNull(ufname))
        {
            s.saveuser_data(u);

        }

        else {
            s.updateuserdata(u);
        }
        return "/index";
    }
}

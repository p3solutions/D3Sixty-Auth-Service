package com.example.p3samlfetch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.extensions.saml2.config.SAMLConfigurer.saml;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${server.ssl.enabled}")
    Boolean ssl_flag;

    @Value("${security.saml2.metadata-url}")
    String metadataUrl;

    @Value("${saml_key_alias}")
    String keyAlias;

    @Value("${saml_keystore_pwd}")
    String password;

    @Value("${server.port}")
    String port;

    @Value("${saml_key_store_file}")
    String keyStoreFilePath;
    String url_pattern;
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        if(ssl_flag==true)
        {
            url_pattern="https";
        }
        if(ssl_flag==false)
        {
            url_pattern="http";
        }
        http
            .authorizeRequests()
                .antMatchers("/saml*").permitAll()
                .anyRequest().authenticated()
                .and()
            .apply(saml())
                .serviceProvider()
                    .keyStore()
                        .storeFilePath(this.keyStoreFilePath)
                        .password(this.password)
                        .keyname(this.keyAlias)
                        .keyPassword(this.password)
                        .and()
                    .protocol(this.url_pattern)
                    .hostname(String.format("%s:%s", "localhost", this.port))
                    .basePath("/")
                    .and()
                .identityProvider()
                .metadataFilePath(this.metadataUrl);
    }
}
package com.jwt.server.service.impl;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.server.domain.UserInfo;

/**
 * 
 * @author zyl
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	UserInfo user = new UserInfo();
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}

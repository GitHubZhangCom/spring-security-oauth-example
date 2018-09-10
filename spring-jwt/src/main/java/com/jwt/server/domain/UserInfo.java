package com.jwt.server.domain;

import lombok.Data;

/**
 * 认证用户
 * @author zyl
 *
 */
@Data
public class UserInfo {
	
    private String id;
    private String username;
    private String password;

    public UserInfo() {
        this.setId("testId");
        this.setUsername("testUsername");
        this.setPassword("testPassword");
    }
}

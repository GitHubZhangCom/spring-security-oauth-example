package com.jwt.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.jwt.server.filter.JwtAuthenticationFilter;
import com.jwt.server.filter.JwtLoginFilter;

/**
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 * 
 * @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER) 在springboot1.5.8的时候该注解是可以用的 具体看源码
 * @author zyl
 *
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//	  //自定义登录
//	  http.cors().and().csrf().disable().authorizeRequests()
//	      .antMatchers("/user/login", "/oauth/authorize").permitAll()
//	      .anyRequest().authenticated()
//	      .and()
//		  .addFilter(new JwtAuthenticationFilter(authenticationManager()));//自定义过滤器
		 
	 //默认登录
//	  http.cors().and().csrf().disable().authorizeRequests()
//	      .antMatchers("/login", "/oauth/authorize").permitAll()
//	      .anyRequest().authenticated()
//	      .and()
//          .requestMatchers().antMatchers("/login","/oauth/authorize")
//	      .and()
//	      .addFilter(new JwtLoginFilter(authenticationManager()));//默认登录过滤器
		
	  
	 //自定义    默认
	  http.cors().and().csrf().disable().authorizeRequests()
          .antMatchers("/user/login","/login", "/oauth/authorize").permitAll()
          .anyRequest().authenticated()
          .and()
          .requestMatchers().antMatchers("/user/login","/login","/oauth/authorize")
          .and()
          .addFilter(new JwtLoginFilter(authenticationManager()))//默认登录过滤器
          .addFilter(new JwtAuthenticationFilter(authenticationManager()));//自定义过滤器
	}

}

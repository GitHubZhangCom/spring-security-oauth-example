package com.jwt.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jwt.server.domain.UserInfo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class LoginController {
	
	/**
	 * 签名key
	 */
	public static final String SIGNING_KEY = "spring-security-@Jwt!&Secret^#";

	@GetMapping("/greeting")
	public String greeting() {
		return "Hello,World!";
	}
	
	/**
	 * 自定义登录
	 * @param user
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public JSONObject login(@RequestBody UserInfo user, HttpServletResponse response) {
		JSONObject  jsonpObject=new JSONObject();
		/**
		 * 自定义生成Token，因为使用了自定义登录，就不会执行JWTLoginFilter了，所以需要字段调用生成token并返给前端
		 */
		// 这里可以根据用户信息查询对应的角色信息，这里为了简单，我直接设置个空list
		List roleList = new ArrayList<>();
		String subject = user.getUsername() + "-" + roleList;
		String token = Jwts.builder().setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000)) // 设置过期时间 365 * 24 * 60
																									// *
																									// 60秒(这里为了方便测试，所以设置了1年的过期时间，实际项目需要根据自己的情况修改)
				.signWith(SignatureAlgorithm.HS512, SIGNING_KEY) // 采用什么算法是可以自己选择的，不一定非要采用HS512
				.compact();
		// 登录成功后，返回token到header里面
		response.addHeader("Authorization", "Bearer " + token);
		jsonpObject.put("access_token", token);
		jsonpObject.put("username", user.getUsername());
		jsonpObject.put("expiration", new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000));
		return jsonpObject;

	}

}

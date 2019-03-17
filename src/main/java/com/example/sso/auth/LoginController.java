package com.example.sso.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    private static final Map<String, String> credentials = new HashMap<>();
    
    public LoginController() {
    	//为了简化，这里使用 HashMap (credentials) 作为用户数据库
        credentials.put("hellokoding", "hellokoding");
        credentials.put("hellosso", "hellosso");
    }

    //进入到login页面
    @RequestMapping("/")
    public String home(){
        return "redirect:/login";
    }
    //进入到login页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //处理用户的登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){
        //验证登录用户的账号和密码
    	if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }

    	//如果通过登录验证， 那么就产生 token  
        String token = JwtUtil.generateToken(signingKey, username);
        //把token设置为cookie的值， 并存放到浏览器中
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        return "redirect:" + redirect;
    }

}
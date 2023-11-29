package com.gymnomnom.gymnomnom.filter;

import com.alibaba.fastjson.JSONObject;
import com.gymnomnom.gymnomnom.pojo.Result;
import com.gymnomnom.gymnomnom.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1. Get URL
        String url = req.getRequestURI().toString();
        log.info("Request URL: {}", url);

        //2. Check login request or signup request
        if (url.contains("login") || url.contains("signup")) {
            log.info("Login and signup, pass");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //3. Get token
        String jwt = req.getHeader("token");

        //4. Check token is null or empty
        if (StringUtils.isEmpty(jwt)) {
            log.info("Empty token");
            Result error = Result.error("Not Logged in");
            String notlogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notlogin);
            return;
        }

        //5. Check JWT validity
        try{
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Parsing failed, return error info");
            Result error = Result.error("Not Logged in");
            String notlogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notlogin);
            return;
        }

        //6. All clear, pass
        log.info("Good login");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

package com.gymnomnom.gymnomnom.aop;

import com.alibaba.fastjson.JSONObject;
import com.gymnomnom.gymnomnom.mapper.TrackMapper;
import com.gymnomnom.gymnomnom.pojo.OperateLog;
import com.gymnomnom.gymnomnom.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private TrackMapper trackMapper;
    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.gymnomnom.gymnomnom.anno.Track)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //Get the jwt in every http request
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);

        //id
        Integer id = (Integer) claims.get("id");

        //Date
        LocalDateTime date = LocalDateTime.now();

        //class name
        String class_name = joinPoint.getTarget().getClass().getName();

        //method_name
        String method_name = joinPoint.getSignature().getName();

        //parameters
        String parameters = Arrays.toString(joinPoint.getArgs());

        //start time
        long start = System.currentTimeMillis();

        //run the target method
        Object result = joinPoint.proceed();

        //end time
        long end = System.currentTimeMillis();

        //return_value
        String return_value = JSONObject.toJSONString(result);



        //record
        OperateLog track_log = new OperateLog(id, date, class_name, method_name, parameters, return_value, end - start);
        log.info("Record login info: {}", track_log);
        trackMapper.insert(track_log);
        return result;
    }
}

package com.gymnomnom.gymnomnom;

import com.gymnomnom.gymnomnom.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class GymnomnomApplicationTests {

    @Test
    void jwtTest() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", "Xiao");
        claims.put("password", "12345");
        String jwt = JwtUtils.generateJwt(claims);
        System.out.println(jwt);
    }

}

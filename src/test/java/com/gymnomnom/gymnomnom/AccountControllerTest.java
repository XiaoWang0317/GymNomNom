package com.gymnomnom.gymnomnom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gymnomnom.gymnomnom.controller.AccountController;
import com.gymnomnom.gymnomnom.controller.BodyController;
import com.gymnomnom.gymnomnom.controller.NutritionController;
import com.gymnomnom.gymnomnom.mapper.LoginMapper;
import com.gymnomnom.gymnomnom.pojo.Body;
import com.gymnomnom.gymnomnom.pojo.User;
import com.gymnomnom.gymnomnom.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(AccountController.class)
@Slf4j
class AccountControllerTest {
    @Autowired
    private AccountController accountController;
    @MockBean
    private AccountService accountService;
    @MockBean
    private LoginMapper loginMapper;
    @Autowired
    private MockMvc mockMvc;

    String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1IiwibmFtZSI6IlhpYW8gV2FuZyIsImlkIjoyLCJleHAiOjE3MDAwNDEyMzJ9.uLJD3tCIh0QRb046kHjCpqu9-7_FkinkdsaRp909llc";
    @Test
    public void testLogin() throws Exception {
        User user = new User();
        user.setName("Xiao Wang");
        user.setPassword("12345");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(jsonPath("$.msg").value("success"));

    }

    @Test
    public void testSignup() throws Exception {
        User user = new User();
        user.setName("John Wick");
        user.setPassword("abcd");
        user.setAge(30);
        user.setGender(1);
        user.setFitness_type(1);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.msg").value("success"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete")
                        .header("token", jwt))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.msg").value("success"));
    }



}



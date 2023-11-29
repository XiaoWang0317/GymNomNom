package com.gymnomnom.gymnomnom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gymnomnom.gymnomnom.controller.BodyController;
import com.gymnomnom.gymnomnom.mapper.BodyMapper;
import com.gymnomnom.gymnomnom.pojo.Body;
import com.gymnomnom.gymnomnom.service.BodyService;
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

@WebMvcTest(BodyController.class)
@Slf4j
public class BodyControllerTest {
//    @Autowired
//    private BodyController bodyController;
//
//    @MockBean
//    private BodyService bodyService;
//
//    @MockBean
//    private BodyMapper bodyMapper;

    @Autowired
    private MockMvc mockMvc;
    String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1IiwibmFtZSI6IlhpYW8gV2FuZyIsImlkIjoyLCJleHAiOjE3MDAwNDEyMzJ9.uLJD3tCIh0QRb046kHjCpqu9-7_FkinkdsaRp909llc";

    @Test
    public void testInputBody() throws Exception {
        Body body = new Body();
        body.setHeight(1.1);
        body.setWeight(1.2);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/inputbody")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON)
                        .header("token", jwt))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.msg").value("success"));
    }

    @Test
    public void testBodyHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/history")
                        .header("token", jwt))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.msg").value("success"));
    }
}

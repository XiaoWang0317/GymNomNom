package com.gymnomnom.gymnomnom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gymnomnom.gymnomnom.controller.NutritionController;
import com.gymnomnom.gymnomnom.mapper.NutritionMapper;
import com.gymnomnom.gymnomnom.pojo.Diet;
import com.gymnomnom.gymnomnom.service.NutritionService;
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

@Slf4j
@WebMvcTest(NutritionController.class)
public class NutritionContollerTest {
    @Autowired
    private NutritionController nutritionController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NutritionService nutritionService;

    @MockBean
    private NutritionMapper nutritionMapper;

    String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1IiwibmFtZSI6IlhpYW8gV2FuZyIsImlkIjoyLCJleHAiOjE3MDAwNDEyMzJ9.uLJD3tCIh0QRb046kHjCpqu9-7_FkinkdsaRp909llc";

    @Test
    public void testInputNut() throws Exception {
        Diet diet = new Diet();
        diet.setCarbs(0.1);
        diet.setCalories(0.2);
        diet.setFat(0.3);
        diet.setProtein(0.4);
        diet.setVa(0.5);
        diet.setVc(0.6);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/inputnut")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(diet))
                        .accept(MediaType.APPLICATION_JSON)
                        .header("token", jwt))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.msg").value("success"));
    }

    @Test
    public void testNutHistory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/historynut")
                        .header("token", jwt))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.msg").value("success"));
    }
}

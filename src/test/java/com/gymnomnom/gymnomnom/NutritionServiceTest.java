package com.gymnomnom.gymnomnom;

import com.gymnomnom.gymnomnom.mapper.BodyMapper;
import com.gymnomnom.gymnomnom.mapper.NutritionMapper;
import com.gymnomnom.gymnomnom.pojo.Diet;
import com.gymnomnom.gymnomnom.service.impl.BodyServiceImpl;
import com.gymnomnom.gymnomnom.service.impl.NutritionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class NutritionServiceTest {
    @Mock
    private NutritionMapper nutritionMapper;

    @InjectMocks
    private NutritionServiceImpl nutritionService;

    @Test
    public void GetHeightArrayTest() {
        Integer id = 2;
        List<Diet> mock_nut_list = new ArrayList<>();
        Mockito.when(nutritionMapper.getNutHistory(id)).thenReturn(mock_nut_list);
        List<Diet> result = nutritionService.getNutHistory(id);
        Mockito.verify(nutritionMapper).getNutHistory(id);
        assertThat(result).isEqualTo(mock_nut_list);
    }
}

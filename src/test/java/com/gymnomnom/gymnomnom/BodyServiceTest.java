package com.gymnomnom.gymnomnom;

import com.gymnomnom.gymnomnom.mapper.AccountMapper;
import com.gymnomnom.gymnomnom.mapper.BodyMapper;
import com.gymnomnom.gymnomnom.pojo.User;
import com.gymnomnom.gymnomnom.service.impl.AccountServiceImpl;
import com.gymnomnom.gymnomnom.service.impl.BodyServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BodyServiceTest {
    @Mock
    private BodyMapper bodyMapper;

    @InjectMocks
    private BodyServiceImpl bodyService;

    @Test
    public void GetHeightArrayTest() {
        Integer id = 2;
        ArrayList<Double> mock_height_list = new ArrayList<>();
        Mockito.when(bodyMapper.getHeights(id)).thenReturn(mock_height_list);
        ArrayList<Double> result = bodyService.getHeightArray(id);
        Mockito.verify(bodyMapper).getHeights(id);
        assertThat(result).isEqualTo(mock_height_list);
    }

    @Test
    public void GetWeightArrayTest() {
        Integer id = 2;
        ArrayList<Double> mock_weight_list = new ArrayList<>();
        Mockito.when(bodyMapper.getWeights(id)).thenReturn(mock_weight_list);
        ArrayList<Double> result = bodyService.getWeightArray(id);
        Mockito.verify(bodyMapper).getWeights(id);
        assertThat(result).isEqualTo(mock_weight_list);
    }

    @Test
    public void GetBmiArrayTest() {
        Integer id = 2;
        ArrayList<Double> mock_bmi_list = new ArrayList<>();
        Mockito.when(bodyMapper.getBmi(id)).thenReturn(mock_bmi_list);
        ArrayList<Double> result = bodyService.getBmiArray(id);
        Mockito.verify(bodyMapper).getBmi(id);
        assertThat(result).isEqualTo(mock_bmi_list);
    }
}

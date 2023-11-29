package com.gymnomnom.gymnomnom;

import com.gymnomnom.gymnomnom.mapper.AccountMapper;
import com.gymnomnom.gymnomnom.pojo.User;
import com.gymnomnom.gymnomnom.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void LoginTest() {
        User Xiao = new User();
        Xiao.setName("Xiao Wang");
        Xiao.setPassword("12345");
        User mockUser = new User();
        Mockito.when(accountMapper.getByNameAndPw(Xiao)).thenReturn(mockUser);
        User result = accountService.login(Xiao);
        Mockito.verify(accountMapper).getByNameAndPw(Xiao);
        assertThat(result).isEqualTo(mockUser);
    }

    @Test
    public void GetAgeByIdTest() {
        Integer id = 2;
        Integer mockAge = -1;
        Mockito.when(accountMapper.getAgeById(id)).thenReturn(mockAge);
        Integer result_age = accountService.getAgeById(id);
        Mockito.verify(accountMapper).getAgeById(id);
        assertThat(result_age).isEqualTo(mockAge);
    }

}

package com.custom;

import com.custom.service.IForwardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by fuyd on 2019-07-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ForwardServiceTest {

    @Autowired
    private IForwardService iForwardService;

    @Test
    public void sendTest() {
        iForwardService.send(Math.random() + "");
    }
}

package org.xxx.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.xxx.modules.service.UserService;


@WebMvcTest
public class MainTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    public void testService() {

    }

    public void testController() {

    }

}

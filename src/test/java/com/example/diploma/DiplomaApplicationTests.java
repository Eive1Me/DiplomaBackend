package com.example.diploma;

import com.example.diploma.services.TaskService;
import com.example.diploma.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DiplomaApplicationTests {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {}

    @Test
    void userServiceNotNullTest() {
        assertNotNull(userService);
        // Проверяем, что наш сервис не равен null
    }

    @Test
    void userGetUsersTest() {
        assertNotNull(userService.readAllEntity(null));
        //Проверяем что сервис отдаёт пользователей
    }

    @Test
    void taskServiceNotNullTest() {
        assertNotNull(taskService);
        // Проверяем, что наш сервис не равен null
    }

    @Test
    void taskGetTasksTest() {
        assertNotNull(taskService.readAllEntity(null));
        //Проверяем что сервис отдаёт задачи
    }


}

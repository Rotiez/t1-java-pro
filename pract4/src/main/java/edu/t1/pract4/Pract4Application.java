package edu.t1.pract4;

import edu.t1.pract4.configuration.GlobalConfiguration;
import edu.t1.pract4.dao.entity.User;
import edu.t1.pract4.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Pract4Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Pract4Application.class);
        context.register(GlobalConfiguration.class);

        UserService userService = context.getBean(UserService.class);
        User testUser = new User(1L, "Test User");

        userService.saveUser(testUser);
        System.out.println(userService.getUserById(testUser.getId()));

        userService.deleteUserById(testUser.getId());
        System.out.println(userService.getAllUsers());
    }
}

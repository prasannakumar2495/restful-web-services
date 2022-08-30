package com.prasanna.rest.webservices.restfulwebservices.dto;

import com.prasanna.rest.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDao {
    private static final List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(++userCount, "PK", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Prasanna", LocalDate.now().minusYears(35)));
        users.add(new User(++userCount, "Kumar", LocalDate.now().minusYears(40)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findSingleUser(int id) {
        Predicate<? super User> predicate = user -> user.getId() == id;
        //return users.stream().filter(user -> user.getId() == id).findFirst().get();
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User createUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public void deleteUser(int id) {

        users.removeIf(user -> user.getId() == id);
    }
}

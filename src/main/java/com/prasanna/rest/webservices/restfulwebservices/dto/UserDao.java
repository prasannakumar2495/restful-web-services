package com.prasanna.rest.webservices.restfulwebservices.dto;

import com.prasanna.rest.webservices.restfulwebservices.model.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDao {
    private static final List<UserDetails> USER_DETAILS = new ArrayList<>();
    private static int userCount = 0;

    static {
        USER_DETAILS.add(new UserDetails(++userCount, "PK", LocalDate.now().minusYears(30)));
        USER_DETAILS.add(new UserDetails(++userCount, "Prasanna", LocalDate.now().minusYears(35)));
        USER_DETAILS.add(new UserDetails(++userCount, "Kumar", LocalDate.now().minusYears(40)));
    }

    public List<UserDetails> findAll() {
        return USER_DETAILS;
    }

    public UserDetails findSingleUser(int id) {
        Predicate<? super UserDetails> predicate = userDetails -> userDetails.getId() == id;
        //return USER_DETAILS.stream().filter(user -> user.getId() == id).findFirst().get();
        return USER_DETAILS.stream().filter(predicate).findFirst().orElse(null);
    }

    public UserDetails createUser(UserDetails userDetails) {
        userDetails.setId(++userCount);
        USER_DETAILS.add(userDetails);
        return userDetails;
    }

    public void deleteUser(int id) {

        USER_DETAILS.removeIf(userDetails -> userDetails.getId() == id);
    }
}

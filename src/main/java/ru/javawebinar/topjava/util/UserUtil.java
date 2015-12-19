package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vvserdiuk on 15.12.2015.
 */
public class UserUtil {

    public static final List<User> USER_LIST = Arrays.asList(
            new User(1, "John", "john@gmail.com", "123456", Role.ROLE_USER, Role.ROLE_USER),
            new User(2, "Mike", "mike@gmail.com", "123456", Role.ROLE_ADMIN, Role.ROLE_ADMIN)
    );
}

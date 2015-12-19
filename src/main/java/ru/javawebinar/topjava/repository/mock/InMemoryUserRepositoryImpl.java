package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.UserUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserUtil.USER_LIST.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        repository.remove(id);
        return repository.containsKey(id);
    }

    @Override
    public User save(User user) {
        LOG.info("create " + user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        return repository.put(user.getId(), user);
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");

        return repository.values()
                .stream()
                .sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);

        List<User> userList = new ArrayList<>(repository.values());
        for ( User u :userList) {
            if (u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
}

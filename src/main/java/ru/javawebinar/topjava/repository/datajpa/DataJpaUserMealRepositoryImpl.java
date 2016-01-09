package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    private   Sort sort = new Sort("datetime");

    @Autowired
    ProxyUserMealRepository proxy;

    @Autowired
    ProxyUserRepository userProxy;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = userProxy.getOne(userId);
        userMeal.setUser(ref);
        if (userMeal.isNew()){
            return proxy.save(userMeal);
        }
        else {
            int updated = proxy.update(userMeal.getDateTime(), userMeal.getCalories(), userMeal.getDescription(), userMeal.getId(), userId);
            if (updated==0){
                return null;
            }
            else return userMeal;
        }

    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) !=0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.findOne(id, userId);
    }

    @Override
    public UserMeal getWithUser(int id, int userId) {
        return proxy.getWithUser(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.findAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(userId, startDate, endDate);
    }
}

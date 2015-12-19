package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    protected final LoggerWrapper LOG = LoggerWrapper.get(getClass());

    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    // <userId>, Set<UserMeal.id>
    Map<Integer, Set<Integer>> matching = new ConcurrentHashMap<>();

    {
//        UserMealsUtil.MEAL_LIST.forEach(um->save(um, 1));
      for (int i = 0; i < 3; i++){
          save(UserMealsUtil.MEAL_LIST.get(i), 1);
      }
        for (int i = 3; i < 6; i++){
            save(UserMealsUtil.MEAL_LIST.get(i), 2);
        }
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        try {
            LOG.info("save" + userMeal + " userId:" + userId);
            if (userMeal.isNew()) {
                userMeal.setId(counter.incrementAndGet());
            }
            repository.put(userMeal.getId(), userMeal);

            Set<Integer> mealsByUserId = matching.get(userId);
            if (mealsByUserId==null){
                mealsByUserId = new HashSet<>();
            }
            mealsByUserId.add(userMeal.getId());
            matching.put(userId, mealsByUserId);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Set<Integer> userMealsIds = matching.get(userId);
        if (userMealsIds==null || !matching.get(userId).contains(id)){
            return false;
        }
        repository.remove(id);
        return true;
    }

    @Override
    public UserMeal get(int id, int userId) {
        Set<Integer> userMealsIds = matching.get(userId);
        if (userMealsIds==null || !matching.get(userId).contains(id)){
            return null;
        }
        return repository.get(id);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        Set<Integer> userMealsIds = matching.get(userId);
        if (userMealsIds==null){
            return null;
        }
        return repository.values()
                .stream()
                .filter(um -> userMealsIds.contains(um.getId()))
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}


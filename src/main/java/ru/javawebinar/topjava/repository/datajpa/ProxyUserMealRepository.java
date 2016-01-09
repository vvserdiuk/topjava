package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by vvserdiuk on 03.01.2016.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId")int userId);

    @Transactional
    @Override
    UserMeal save(UserMeal userMeal);

    @Transactional
    @Modifying
    @Query("UPDATE UserMeal m SET m.dateTime = :datetime, m.calories= :calories," +
            "m.description=:desc where m.id=:id and m.user.id=:userId")
    int update(@Param("datetime") LocalDateTime datetime, @Param("calories") int calories,
                    @Param("desc") String description, @Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId")
    UserMeal findOne(@Param("id") int id, @Param("userId")int userId);

    @Query("SELECT m FROM UserMeal m JOIN FETCH m.user WHERE m.id=:id AND m.user.id=:userId")
    UserMeal getWithUser(@Param("id") int id, @Param("userId")int userId);

    @Query("SELECT m FROM UserMeal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<UserMeal> findAll(@Param("userId")int userId);

    @Query("SELECT m from UserMeal m WHERE m.user.id=:userId " +
            " AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<UserMeal> getBetween(@Param("userId")int userId, @Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}

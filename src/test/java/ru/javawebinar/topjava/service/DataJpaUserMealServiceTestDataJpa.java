package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by vvserdiuk on 03.01.2016.
 */
@ActiveProfiles({Profiles.HSQLDB, Profiles.DATAJPA})
public class DataJpaUserMealServiceTestDataJpa extends UserMealServiceTest {
}

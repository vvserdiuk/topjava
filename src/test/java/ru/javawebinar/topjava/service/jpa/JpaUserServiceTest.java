package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.service.CachedUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaUserServiceTest extends CachedUserServiceTest {
}
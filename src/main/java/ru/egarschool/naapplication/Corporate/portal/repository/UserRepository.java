package ru.egarschool.naapplication.Corporate.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.egarschool.naapplication.Corporate.portal.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

    void update(User user, User newuser);

    void create(User user);
}

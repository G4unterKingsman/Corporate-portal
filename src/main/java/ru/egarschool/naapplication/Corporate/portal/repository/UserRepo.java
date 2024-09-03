package ru.egarschool.naapplication.Corporate.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egarschool.naapplication.Corporate.portal.entity.UserAccount;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByUsername(String username);
}

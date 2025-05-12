package ru.gaunter.app.corporate.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gaunter.app.corporate.portal.entity.UserAccount;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByUsername(String username);

    boolean existsByUsername(String username);
}

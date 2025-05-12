package ru.gaunter.app.corporate.portal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gaunter.app.corporate.portal.entity.UserAccount;
import ru.gaunter.app.corporate.portal.entity.UserAccountDetails;
import ru.gaunter.app.corporate.portal.repository.UserRepo;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccount = userRepo.findByUsername(username);
        return userAccount.map(UserAccountDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
    }
}

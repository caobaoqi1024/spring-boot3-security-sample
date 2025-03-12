package dev.mcdd.config.security;

import dev.mcdd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsServiceImpl.loadUserByUsername for {}", username);
        return userRepository.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .disabled(!user.getEnabled())
                        .credentialsExpired(!user.getEnabled())
                        .accountLocked(!user.getEnabled())
                        .accountExpired(!user.getEnabled())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

}

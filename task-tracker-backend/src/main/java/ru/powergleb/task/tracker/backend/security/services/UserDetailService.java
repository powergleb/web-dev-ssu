package ru.powergleb.task.tracker.backend.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.powergleb.task.tracker.backend.security.model.User;
import ru.powergleb.task.tracker.backend.security.repositories.UserRepository;
import ru.powergleb.task.tracker.backend.security.securityDetails.TaskTrackerUserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        return new TaskTrackerUserDetails(user.get());
    }
}

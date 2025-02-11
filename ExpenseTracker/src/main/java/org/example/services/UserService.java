package org.example.services;

import org.example.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserService {
    AppUser saveUser(AppUser user);
    AppUser findByUsername(String username);
    Optional<AppUser> findUserById(Long id);

}

package org.example.services;

import org.example.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    List<AppUser> getAllUsers();
}

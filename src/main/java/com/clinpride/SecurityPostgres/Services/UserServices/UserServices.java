package com.clinpride.SecurityPostgres.Services.UserServices;

import com.clinpride.SecurityPostgres.models.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    AppUser viewProfile(String email);
    AppUser updateProfile(String email,AppUser appUser);
}

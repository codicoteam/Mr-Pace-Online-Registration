package com.clinpride.SecurityPostgres.Services.UserServices;

import com.clinpride.SecurityPostgres.UserRepo.UserRepository;
import com.clinpride.SecurityPostgres.models.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServicesImpl implements  UserServices{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser viewProfile(String email) {
        Optional<AppUser> optionalUser = repository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NoSuchElementException("User not found");
        }
    }

    @Override
    public AppUser updateProfile(String email, AppUser appUser) {
        Optional<AppUser> optionalUser = repository.findByEmail(email);
        if (optionalUser.isPresent()) {
            AppUser existingUser = optionalUser.get();
            // Update the fields that can be modified
            existingUser.setEmail(appUser.getEmail());
            existingUser.setIdNumber(appUser.getIdNumber());
            existingUser.setAuthenticateUser(appUser.getAuthenticateUser());
            existingUser.setLastName(appUser.getLastName());
            existingUser.setFirstName(appUser.getFirstName());
            existingUser.setShowUser(appUser.getShowUser());
            existingUser.setPhoneNumber(appUser.getPhoneNumber());
            existingUser.setShowUser(appUser.getShowUser());
            existingUser.setRoleName(appUser.getRoleName());

            // Check if a new password is provided and encode it
            if (appUser.getPassword() != null) {
                String encodedPassword = passwordEncoder.encode(appUser.getPassword());
                existingUser.setPassword(encodedPassword);
            }

            return repository.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found");
        }


    }

}

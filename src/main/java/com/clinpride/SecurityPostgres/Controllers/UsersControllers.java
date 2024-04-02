package com.clinpride.SecurityPostgres.Controllers;

import com.clinpride.SecurityPostgres.Services.UserServices.UserServices;
import com.clinpride.SecurityPostgres.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersControllers {
    private final UserServices userService;

    public UsersControllers(UserServices userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<AppUser> getUserProfile(@PathVariable String email) {
        AppUser user = userService.viewProfile(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{email}")
    public ResponseEntity<AppUser> updateUser(@PathVariable String email, @RequestBody AppUser updatedUser) {
        AppUser user = userService.updateProfile(email, updatedUser);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

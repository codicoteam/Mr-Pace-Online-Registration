package com.clinpride.SecurityPostgres.Services;

import com.clinpride.SecurityPostgres.Config.Auth.AuthenticationResponse;
import com.clinpride.SecurityPostgres.Config.JwtService;
import com.clinpride.SecurityPostgres.UserRepo.UserRepository;
import com.clinpride.SecurityPostgres.models.AppUser;
import com.clinpride.SecurityPostgres.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender javaMailSender;


    public AuthenticationResponse register(AppUser appUser) {
        Optional<AppUser> existingUser = repository.findByEmail(appUser.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        else {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(appUser.getEmail());
            message.setSubject("Welcome to Mr Pace Online Registration");
            message.setText(" Dear " + appUser.getFirstName()+ "\n" +
                    "\n" +
                    "Thank you for joining Mr Pace Online Race Registration as an administrator " +
                    "\n You role is to check all the athlete that claims that they have registered Online and verify if they have successfully do so\n" +
                    " Enjoy a seamless shopping experience, personalized recommendations, exclusive offers, and dedicated customer support. ");
            javaMailSender.send(message);

            var user = AppUser.builder()
                    .firstName(appUser.getFirstName())
                    .lastName(appUser.getLastName())
                    .email(appUser.getEmail())
                    .authenticateUser(appUser.getAuthenticateUser())
                    .phoneNumber(appUser.getPhoneNumber())
                    .showUser(appUser.getShowUser())
                    .idNumber(appUser.getIdNumber())
                    .password(passwordEncoder.encode(appUser.getPassword()))
                    .role(Role.USER)
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }
    }

    public AuthenticationResponse authenticate(AppUser appUser) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    appUser.getEmail(),
                    appUser.getPassword()
            )
    );
            var user = repository.findByEmail(appUser.getEmail())
                    .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

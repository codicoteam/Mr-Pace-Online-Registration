package com.clinpride.SecurityPostgres.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "AppUsers")

public class AppUser implements UserDetails {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roleName;
    private String idNumber;
    private String phoneNumber;
    private Boolean showUser;
    private Boolean authenticateUser;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

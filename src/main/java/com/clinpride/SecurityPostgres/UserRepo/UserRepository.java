package com.clinpride.SecurityPostgres.UserRepo;

import com.clinpride.SecurityPostgres.models.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
        Optional<AppUser> findByEmail(String email);
        }






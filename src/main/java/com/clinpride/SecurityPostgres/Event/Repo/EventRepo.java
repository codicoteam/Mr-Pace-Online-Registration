package com.clinpride.SecurityPostgres.Event.Repo;


import com.clinpride.SecurityPostgres.Event.Model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends MongoRepository<Event, String> {
    void deleteById(String id);
}



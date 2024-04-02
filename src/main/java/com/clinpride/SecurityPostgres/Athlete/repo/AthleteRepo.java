package com.clinpride.SecurityPostgres.Athlete.repo;

import com.clinpride.SecurityPostgres.Athlete.model.Athlete;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepo extends MongoRepository<Athlete, String> {
    List<Athlete> findAllByEventId(String eventId);

}

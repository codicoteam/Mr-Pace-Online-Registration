package com.clinpride.SecurityPostgres.Athlete.service;
import com.clinpride.SecurityPostgres.Athlete.model.Athlete;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AthleteServices {
    Athlete createAthlete(Athlete athlete);
    List<Athlete> getAllAthlete();
    String updateAthlete(String athleteId,Athlete athlete);
    String deleteAthlete(String athleteId);
    String deleteManyAthletes(List<String> athleteIds);
     List<Athlete> findAllAthletesByEventId(String eventId);
}

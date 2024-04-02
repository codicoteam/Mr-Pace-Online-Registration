package com.clinpride.SecurityPostgres.Athlete.service.Implementor;

import com.clinpride.SecurityPostgres.Athlete.model.Athlete;
import com.clinpride.SecurityPostgres.Athlete.repo.AthleteRepo;
import com.clinpride.SecurityPostgres.Athlete.service.AthleteServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AthleteImplementor implements AthleteServices {
    private final AthleteRepo athleteRepo;
    @Override
    public Athlete createAthlete(Athlete athlete) {
        return athleteRepo.save(athlete);
    }

    @Override
    public List<Athlete> getAllAthlete() {
        return athleteRepo.findAll();
    }

    @Override
    public String updateAthlete(String athleteId, Athlete athlete) {
        Optional<Athlete> existingAthlete = athleteRepo.findById(athleteId);
        if(existingAthlete.isPresent()){
            Athlete updateAthlete = existingAthlete.get();
            updateAthlete.setId(athlete.getId());
            updateAthlete.setEmail(athlete.getEmail());
            updateAthlete.setBooleanStatus(athlete.isBooleanStatus());
            updateAthlete.setGender(athlete.getGender());
            updateAthlete.setCreatedAt(athlete.getCreatedAt());
            updateAthlete.setCreatedAtTime(athlete.getCreatedAtTime());
            updateAthlete.setLastname(athlete.getLastname());
            updateAthlete.setDateOfBirth(athlete.getDateOfBirth());
            updateAthlete.setEventName(athlete.getEventName());
            updateAthlete.setEventId(athlete.getEventId());
            updateAthlete.setFirstName(athlete.getFirstName());
            updateAthlete.setIdNumber(athlete.getIdNumber());
            updateAthlete.setShirtSize(athlete.getShirtSize());
            updateAthlete.setPhoneNumber(athlete.getPhoneNumber());
            updateAthlete.setDateOfBirth(athlete.getDateOfBirth());
            updateAthlete.setPassportNumber(athlete.getPassportNumber());
            updateAthlete.setEventDistance(athlete.getEventDistance());
            athleteRepo.save(updateAthlete);
            return "athlete updated successfully ";
        }
        else {
            return "athlete not found";
        }
    }

    @Override
    public String deleteAthlete(String athleteId) {
        Optional<Athlete> existingAthlete = athleteRepo.findById(athleteId);
        if(existingAthlete.isPresent()){
            athleteRepo.deleteById(athleteId);
            return "athlete deleted successfully";
        }
        else {
            return "athlete not found";
        }
    }

    @Override
    public String deleteManyAthletes(List<String> athleteIds) {
        try {
            athleteRepo.deleteAllById(athleteIds);
            return "Athletes deleted successfully";
        } catch (Exception e) {
            return "Failed to delete athletes";
        }
    }

    @Override
    public List<Athlete> findAllAthletesByEventId(String eventId) {
        return athleteRepo.findAllByEventId(eventId);
    }
}

package com.clinpride.SecurityPostgres.Athlete.controller;

import com.clinpride.SecurityPostgres.Athlete.model.Athlete;
import com.clinpride.SecurityPostgres.Athlete.service.AthleteServices;
import com.clinpride.SecurityPostgres.Event.Model.Event;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/mr-pace-online-registration/athlete")
public class AthleteController {
    private final AthleteServices athleteServices;
    @PostMapping("/create")
    public ResponseEntity<Athlete> createAthlete(@RequestBody Athlete athlete){
        Athlete saveAthlete = athleteServices.createAthlete(athlete);
        return ResponseEntity.ok(saveAthlete);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Athlete>> getAllAthletes(){
        List<Athlete> getAllAthlete = athleteServices.getAllAthlete();
        return ResponseEntity.ok(getAllAthlete);
    }

    @PostMapping("/update")
    public ResponseEntity<String> createUpdateEvent(@RequestBody Athlete athlete) {
        try {
            String updateResult = athleteServices.updateAthlete(athlete.getId(), athlete);
            return ResponseEntity.ok(updateResult);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PostMapping("/delete-athlete")
    public ResponseEntity<String> deleteAthlete(@RequestParam  String id){
        try {
            String updateForEvent = athleteServices.deleteAthlete(id);
            return ResponseEntity.ok(updateForEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PostMapping("/delete-many-athlete")
    public ResponseEntity<String> deleteManyAthlete(@RequestBody  List<String> manyId){
        try {
            String updateForEvent = athleteServices.deleteManyAthletes(manyId);
            return ResponseEntity.ok(updateForEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @GetMapping("/get-all-by-athlete-id")
    public ResponseEntity<List<Athlete>> getAllAthletesByEventId(@RequestParam String eventId){
        List<Athlete> getAllAthlete = athleteServices.findAllAthletesByEventId(eventId);
        return ResponseEntity.ok(getAllAthlete);
    }
}

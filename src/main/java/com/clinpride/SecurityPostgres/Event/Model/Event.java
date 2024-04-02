package com.clinpride.SecurityPostgres.Event.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@AllArgsConstructor
@Data
@Document
public class Event {
    @Id
    private String id;
    private String name;
    private String description;
    private String dateOfEvent;
    private String timeOfEvent;
    private String eventCategory;
    private String status;
    private boolean booleanStatus;
    private List<String> images;
    private String location;
    private String longitude;
    private String latitude;
    private List<RacePackCollection> racePackCollections;
    private List<Race> racesAvailable;
    private String createdAt = getCurrentDateAsString();

    public Event() {
        this.createdAt = getCurrentDateAsString();
    }

    private String getCurrentDateAsString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }
}
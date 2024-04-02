package com.clinpride.SecurityPostgres.Athlete.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Data
@AllArgsConstructor
@Document
public class Athlete {
    @Id
    private String id;
    private String firstName;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String idNumber;
    private String passportNumber;
    private boolean booleanStatus;
    private String gender;
    private String shirtSize;
    private String dateOfBirth;
    private String eventName;
    private String eventId;
    private String createdAt = getCurrentDateAsString();
    private String createdAtTime;
    private String eventDistance;
    public Athlete() {
        this.createdAt = getCurrentDateAsString();
        this.createdAtTime = getCurrentTimeAsString();
    }

    private String getCurrentDateAsString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }
    private String getCurrentTimeAsString() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }
}

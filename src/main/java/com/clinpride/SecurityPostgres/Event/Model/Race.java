package com.clinpride.SecurityPostgres.Event.Model;

import lombok.Data;

@Data
class Race {
    private String raceDistance;
    private String registrationAmount;
    private int raceRegistration;
    private String status;
    private int availableTickets;
}

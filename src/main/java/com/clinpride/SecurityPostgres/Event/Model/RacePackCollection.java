package com.clinpride.SecurityPostgres.Event.Model;

import lombok.Data;

@Data
class RacePackCollection {
    private String location;
    private String description;
    private String dateOfCollection;
    private String startTime;
}

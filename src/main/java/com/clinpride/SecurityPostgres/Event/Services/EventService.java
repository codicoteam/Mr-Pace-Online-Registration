package com.clinpride.SecurityPostgres.Event.Services;

import com.clinpride.SecurityPostgres.Event.Model.Event;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface EventService {
     List<Event> getAllEvents();
     Event createEvent(Event event);
     String updateEvent(String eventId,Event event);
     String deleteEvent(String eventId);
}

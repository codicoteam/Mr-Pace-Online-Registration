package com.clinpride.SecurityPostgres.Event.Services.ServiceImpl;

import com.clinpride.SecurityPostgres.Event.Model.Event;
import com.clinpride.SecurityPostgres.Event.Repo.EventRepo;
import com.clinpride.SecurityPostgres.Event.Services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventImpl implements EventService {
    private  final EventRepo eventRepo;
    @Override
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepo.save(event);
    }


    @Override
    public String updateEvent(String eventId, Event event) {
        Optional<Event> existingEvent = eventRepo.findById(eventId);
        if(existingEvent.isPresent()){
            Event eventUpdate = existingEvent.get();
            eventUpdate.setDateOfEvent(event.getDateOfEvent());
            eventUpdate.setName(event.getName());
            eventUpdate.setEventCategory(event.getEventCategory());
            eventUpdate.setId(event.getId());
            eventUpdate.setDescription(event.getDescription());
            eventUpdate.setDescription(event.getDescription());
            eventUpdate.setLocation(event.getLocation());
            eventUpdate.setImages(event.getImages());
            eventUpdate.setLatitude(event.getLatitude());
            eventUpdate.setRacePackCollections(event.getRacePackCollections());
            eventUpdate.setRacesAvailable(event.getRacesAvailable());
            eventUpdate.setCreatedAt(event.getCreatedAt());
            eventUpdate.setStatus(event.getStatus());
            eventUpdate.setBooleanStatus(event.isBooleanStatus());
            eventRepo.save(eventUpdate);
            return "Event saved successfully";
        }else {
            return "event does not exist";
        }
    }

    @Override
    public String deleteEvent(String eventId) {
        Optional<Event> existingEvent = eventRepo.findById(eventId);
        if (existingEvent.isPresent()) {
            eventRepo.deleteById(eventId);
            return "Event deleted successfully";
        } else {
            return "Event not found";
        }
    }
}

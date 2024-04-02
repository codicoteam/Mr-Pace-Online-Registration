package com.clinpride.SecurityPostgres.Event.Controllers;

import com.clinpride.SecurityPostgres.Event.Model.Event;
import com.clinpride.SecurityPostgres.Event.Services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/mr-pace-online-registration")
public class EventController {
    private final EventService eventService;

    @PostMapping("/create-event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        Event saveEvent = eventService.createEvent(event);
        return ResponseEntity.ok(saveEvent);
    }

    @GetMapping("/get-event")
    public ResponseEntity<List<Event>> getAllEvent(){
            List<Event> getAllEvents = eventService.getAllEvents();
            return ResponseEntity.ok(getAllEvents);
    }

    @PostMapping("/update-events")
    public ResponseEntity<String> createUpdateEvent(@RequestBody Event event) {
        try {
            String eventId = event.getId();
            String updateResult = eventService.updateEvent(eventId, event);
            return ResponseEntity.ok(updateResult);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PostMapping("/delete-event")
    public ResponseEntity<String> deleteEvent(@RequestParam  String id){
        try {
            String updateForEvent = eventService.deleteEvent(id);
            return ResponseEntity.ok(updateForEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}

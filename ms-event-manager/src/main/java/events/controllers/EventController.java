package events.controllers;

import events.entities.DTOs.CreateEventDTO;
import events.entities.DTOs.UpdateEventDTO;
import events.entities.Event;
import events.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create-event")
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventDTO event) {
        Event createdEvent = eventService.createEvent(event);

        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/get-all-events")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/get-event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id){
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/get-all-events/sorted")
    public ResponseEntity<List<Event>> getAllEventsSorted() {
        List<Event> events = eventService.getAllEventsSortedByName();
        return ResponseEntity.ok(events);
    }

    @PutMapping("/update-event/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody UpdateEventDTO event){
        Event updatedEvent = eventService.updateEvent(event, id);

        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/delete-event/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable String id) {
        Event deletedEvent = eventService.deleteEvent(id);
        return ResponseEntity.ok(deletedEvent);
    }



}

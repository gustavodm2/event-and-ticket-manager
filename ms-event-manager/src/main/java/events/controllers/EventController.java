package events.controllers;

import events.entities.DTOs.CreateEventDTO;
import events.entities.DTOs.UpdateEventDTO;
import events.entities.Event;
import events.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new event", description = "Endpoint to create a new event with name, date/time, and ZIP code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successfully created", content = @Content(schema = @Schema(implementation = Event.class))),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create-event")
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventDTO event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    @Operation(summary = "Get all events", description = "Returns a list of all registered events.")
    @ApiResponse(responseCode = "200", description = "List of events successfully retrieved")
    @GetMapping("/get-all-events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "Get an event by ID", description = "Fetches a specific event by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event successfully retrieved", content = @Content(schema = @Schema(implementation = Event.class))),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/get-event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
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

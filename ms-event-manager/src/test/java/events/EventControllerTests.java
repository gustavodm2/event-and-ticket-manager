package events;
import events.controllers.EventController;
import events.entities.DTOs.CreateEventDTO;
import events.entities.DTOs.UpdateEventDTO;
import events.entities.Event;
import events.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventControllerTests {
    @InjectMocks
    private EventController eventController;

    @Mock
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEvent_ShouldReturnCreatedEvent() {
        CreateEventDTO createEventDTO = new CreateEventDTO();
        createEventDTO.setEventName("Test Event");

        Event mockEvent = new Event();
        mockEvent.setId("1");
        mockEvent.setEventName("Test Event");

        when(eventService.createEvent(createEventDTO)).thenReturn(mockEvent);

        ResponseEntity<Event> response = eventController.createEvent(createEventDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockEvent, response.getBody());
        verify(eventService, times(1)).createEvent(createEventDTO);
    }

    @Test
    void getAllEvents_ShouldReturnListOfEvents() {
        Event event1 = new Event();
        event1.setId("1");
        event1.setEventName("Test Event 1");

        Event event2 = new Event();
        event2.setId("2");
        event2.setEventName("Test Event 2");

        List<Event> mockEvents = Arrays.asList(event1, event2);

        when(eventService.getAllEvents()).thenReturn(mockEvents);

        ResponseEntity<List<Event>> response = eventController.getAllEvents();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockEvents, response.getBody());
        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    void getEventById_ShouldReturnEvent() {
        String eventId = "1";
        Event mockEvent = new Event();
        mockEvent.setId(eventId);
        mockEvent.setEventName("Event 1");

        when(eventService.getEventById(eventId)).thenReturn(mockEvent);

        ResponseEntity<Event> response = eventController.getEventById(eventId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockEvent, response.getBody());
        verify(eventService, times(1)).getEventById(eventId);
    }

    @Test
    void updateEvent_ShouldReturnUpdatedEvent() {
        String eventId = "1";
        UpdateEventDTO updateEventDTO = new UpdateEventDTO();
        updateEventDTO.setEventName("Updated Event");

        Event updatedEvent = new Event();
        updatedEvent.setId(eventId);
        updatedEvent.setEventName("Updated Event");

        when(eventService.updateEvent(updateEventDTO, eventId)).thenReturn(updatedEvent);

        ResponseEntity<Event> response = eventController.updateEvent(eventId, updateEventDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedEvent, response.getBody());
        verify(eventService, times(1)).updateEvent(updateEventDTO, eventId);
    }

    @Test
    void deleteEvent_ShouldReturnDeletedEvent() {
        String eventId = "1";
        Event deletedEvent = new Event();
        deletedEvent.setId(eventId);
        deletedEvent.setEventName("Deleted Event");

        when(eventService.deleteEvent(eventId)).thenReturn(deletedEvent);

        ResponseEntity<Event> response = eventController.deleteEvent(eventId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(deletedEvent, response.getBody());
        verify(eventService, times(1)).deleteEvent(eventId);
    }

    @Test
    void getAllEventsSorted_ShouldReturnSortedEvents() {
        Event event1 = new Event();
        event1.setId("2");
        event1.setEventName("a");

        Event event2 = new Event();
        event2.setId("1");
        event2.setEventName("b");

        List<Event> sortedEvents = Arrays.asList(event1, event2);

        when(eventService.getAllEventsSortedByName()).thenReturn(sortedEvents);

        ResponseEntity<List<Event>> response = eventController.getAllEventsSorted();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("a", response.getBody().get(0).getEventName());
        assertEquals("b", response.getBody().get(1).getEventName());
        verify(eventService, times(1)).getAllEventsSortedByName();
    }


}

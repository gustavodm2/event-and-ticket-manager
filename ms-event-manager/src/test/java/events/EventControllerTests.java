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
}

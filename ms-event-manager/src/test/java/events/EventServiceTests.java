package events;

import events.entities.DTOs.CreateEventDTO;
import events.entities.DTOs.UpdateEventDTO;
import events.entities.Event;
import events.mapper.CreateEventMapper;
import events.mapper.UpdateEventMapper;
import events.repositories.EventRepository;
import events.services.CepService;
import events.services.EventService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventServiceTests {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private CepService cepService;

    @Mock
    private CreateEventMapper createEventMapper;

    @Mock
    private UpdateEventMapper updateEventMapper;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEvent_ShouldSaveEvent() {
        CreateEventDTO dto = new CreateEventDTO();
        Event event = new Event();
        when(createEventMapper.toEntity(dto)).thenReturn(event);
        when(eventRepository.save(event)).thenReturn(event);

        Event createdEvent = eventService.createEvent(dto);

        assertNotNull(createdEvent);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void getAllEvents_ShouldReturnListOfEvents() {
        List<Event> events = List.of(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.getAllEvents();

        assertEquals(2, result.size());
        verify(eventRepository, times(1)).findAll();
    }

}

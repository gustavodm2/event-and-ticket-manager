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

    @Test
    void getEventById_ShouldReturnEventIfExists() {
        String id = "123";
        Event event = new Event();
        event.setId(id);
        when(eventRepository.findById(id)).thenReturn(Optional.of(event));

        Event result = eventService.getEventById(id);

        assertEquals(id, result.getId());
        verify(eventRepository, times(1)).findById(id);
    }

    @Test
    void getEventById_ShouldThrowIfEventDoesNotExist() {
        String id = "123";
        when(eventRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> eventService.getEventById(id));
        verify(eventRepository, times(1)).findById(id);
    }

    @Test
    void updateEvent_ShouldUpdateAndSaveEvent() {
        String id = "123";
        UpdateEventDTO dto = new UpdateEventDTO();
        Event event = new Event();
        when(eventRepository.findById(id)).thenReturn(Optional.of(event));
        doNothing().when(updateEventMapper).updateEntity(dto, event);
        when(eventRepository.save(event)).thenReturn(event);

        Event updatedEvent = eventService.updateEvent(dto, id);

        assertNotNull(updatedEvent);
        verify(eventRepository, times(1)).findById(id);
        verify(updateEventMapper, times(1)).updateEntity(dto, event);
        verify(eventRepository, times(1)).save(event);
    }

}

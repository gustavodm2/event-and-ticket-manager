package events.services;

import events.entities.DTOs.CreateEventDTO;
import events.entities.DTOs.UpdateEventDTO;
import events.entities.Event;
import events.mapper.CreateEventMapper;
import events.mapper.UpdateEventMapper;
import events.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CepService cepService;
    private final CreateEventMapper createEventMapper;
    private final UpdateEventMapper updateEventMapper;

    public EventService(EventRepository eventRepository, CepService cepService, CreateEventMapper createEventMapper, UpdateEventMapper updateEventMapper) {
        this.eventRepository = eventRepository;
        this.cepService = cepService;
        this.createEventMapper = createEventMapper;
        this.updateEventMapper = updateEventMapper;
    }

    public Event createEvent(CreateEventDTO dto) {
        Event event = createEventMapper.toEntity(dto);

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + id));
    }

    public List<Event> getAllEventsSortedByName() {
        return eventRepository.findAll(Sort.by(Sort.Order.asc("eventName")));
    }


    public Event updateEvent(UpdateEventDTO dto, String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + id));

        updateEventMapper.updateEntity(dto, event);

        return eventRepository.save(event);
    }

    public Event deleteEvent(String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + id));

        event.setDeleted(true);

        eventRepository.save(event);

        return event;

    }

}

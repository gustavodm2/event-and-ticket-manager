package events.services;

import events.entities.Address;
import events.entities.DTOs.CreateEventDTO;
import events.entities.Event;
import events.mapper.EventMapper;
import events.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CepService cepService;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, CepService cepService, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.cepService = cepService;
        this.eventMapper = eventMapper;
    }

    public Event createEvent(CreateEventDTO dto) {
        Event event = eventMapper.toEntity(dto);

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(String id){
        return eventRepository.findById(id);
    }
}

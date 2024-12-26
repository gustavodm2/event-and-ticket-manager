package events.services;

import events.entities.Address;
import events.entities.Event;
import events.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CepService cepService;

    public EventService(EventRepository eventRepository, CepService cepService) {
        this.eventRepository = eventRepository;
        this.cepService = cepService;
    }

    public Event createEvent(Event event) {

        Address address = cepService.getAddressFromCep(event.getCep().getCep());
        event.setCep(address);

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
}

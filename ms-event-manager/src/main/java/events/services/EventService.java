package events.services;

import events.clients.TicketClient;
import events.entities.Address;
import events.entities.DTOs.CheckResponse;
import events.entities.DTOs.CreateEventDTO;
import events.entities.DTOs.TicketDTO;
import events.entities.DTOs.UpdateEventDTO;
import events.entities.Event;
import events.mapper.CreateEventMapper;
import events.mapper.UpdateEventMapper;
import events.repositories.EventRepository;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CepService cepService;

    @Autowired
    private CreateEventMapper createEventMapper;

    @Autowired
    private UpdateEventMapper updateEventMapper;

    @Autowired
    private TicketClient ticketClient;



    public Event createEvent(CreateEventDTO dto) {
        Event event = createEventMapper.toEntity(dto);
        Address address = cepService.getAddressFromCep(dto.getCep());
        if (address.getLocalidade() == null) {
            throw new IllegalArgumentException("Invalid CEP: Address not found.");
        }

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

        List<TicketDTO> tickets = ticketClient.getTicketsByEventId(event.getId());

        boolean hasActiveTickets = tickets.stream()
                .anyMatch(ticket -> !"INACTIVE".equalsIgnoreCase(ticket.getStatus()));

        if (hasActiveTickets) {
            throw new IllegalStateException("Cannot delete event with active tickets");
        }

        event.setDeleted(true);
        return eventRepository.save(event);
    }


}

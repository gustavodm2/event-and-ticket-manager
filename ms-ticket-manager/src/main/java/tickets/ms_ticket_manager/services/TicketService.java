package tickets.ms_ticket_manager.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.ms_ticket_manager.clients.EventClient;
import tickets.ms_ticket_manager.entities.DTOs.CreateTicketDTO;
import tickets.ms_ticket_manager.entities.DTOs.EventDTO;
import tickets.ms_ticket_manager.entities.DTOs.UpdateTicketDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.mappers.CreateTicketMap;
import tickets.ms_ticket_manager.mappers.UpdateTicketMap;
import tickets.ms_ticket_manager.producers.TicketProducer;
import tickets.ms_ticket_manager.repositories.TicketRepository;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private CreateTicketMap createTicketMap;

    @Autowired
    private UpdateTicketMap updateTicketMap;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketProducer ticketProducer;

    @Autowired
    private EventClient eventClient;

    public Ticket createTicket(CreateTicketDTO createTicketDTO){


        Ticket ticket = createTicketMap.toEntity(createTicketDTO);

        ticketProducer.publishMessageEmail(ticket);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByEventId(String eventId) {

        EventDTO eventDTO = eventClient.getEventById(eventId);

        List<Ticket> tickets = ticketRepository.findByEventId(eventDTO.getId());

        if (tickets.isEmpty()) {
            throw new EntityNotFoundException("No tickets found for event with ID " + eventId);
        }

        return tickets;
    }

    public Ticket getTicketById(String id){
        return ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + id));
    }

    public Ticket updateTicket(UpdateTicketDTO updateTicketDTO, String id){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + id));

        updateTicketMap.toEntity(updateTicketDTO, ticket);

        return ticketRepository.save(ticket);

    }

    public Ticket deleteTicket(String id){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + id));

        ticket.setDeleted(true);

        return ticketRepository.save(ticket);
    }


}

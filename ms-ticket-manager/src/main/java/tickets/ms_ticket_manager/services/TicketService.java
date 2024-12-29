package tickets.ms_ticket_manager.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.ms_ticket_manager.entities.DTOs.CreateTicketDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.mappers.CreateTicketMap;
import tickets.ms_ticket_manager.repositories.TicketRepository;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private CreateTicketMap createTicketMap;

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(CreateTicketDTO createTicketDTO){
        Ticket ticket = createTicketMap.toEntity(createTicketDTO);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(String id){
        return ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + id));
    }

}

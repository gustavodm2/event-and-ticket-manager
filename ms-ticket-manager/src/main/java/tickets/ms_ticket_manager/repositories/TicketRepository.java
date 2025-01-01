package tickets.ms_ticket_manager.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tickets.ms_ticket_manager.entities.Ticket;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByEventId(String eventId);
}

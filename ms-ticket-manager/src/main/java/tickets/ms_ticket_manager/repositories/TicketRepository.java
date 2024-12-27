package tickets.ms_ticket_manager.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tickets.ms_ticket_manager.entities.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}

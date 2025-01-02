package tickets.ms_ticket_manager.entities.DTOs;

import lombok.Data;

@Data
public class CheckTicketByEventResponse {

    private String eventId;
    private boolean hasTickets;

}

package tickets.ms_ticket_manager.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tickets.ms_ticket_manager.entities.DTOs.EventDTO;
import tickets.ms_ticket_manager.entities.DTOs.UpdateTicketDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.services.EventService;

@Component
public class UpdateTicketMap {

    @Autowired
    private EventService eventService;

    public void toEntity(UpdateTicketDTO dto, Ticket ticket) {
        if (dto.getCustomerName() != null) {
            ticket.setCustomerName(dto.getCustomerName());
        }

        if (dto.getCpf() != null) {
            ticket.setCpf(dto.getCpf());
        }

        if (dto.getCustomerMail() != null) {
            ticket.setCustomerMail(dto.getCustomerMail());
        }

        if (dto.getEventId() != null) {
            EventDTO event = eventService.getEventFromId(dto.getEventId());
            ticket.setEvent(event);
        }

        if (dto.getBRLAmount() != null) {
            ticket.setBrlamount(dto.getBRLAmount());
        }

        if (dto.getUSDAmount() != null) {
            ticket.setUsdamount(dto.getUSDAmount());
        }

        if (dto.getStatus() != null) {
            ticket.setStatus(dto.getStatus());
        }
    }
}


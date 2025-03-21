package tickets.ms_ticket_manager.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tickets.ms_ticket_manager.entities.DTOs.CreateTicketDTO;
import tickets.ms_ticket_manager.entities.DTOs.EventDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.services.EventService;

@Component
public class CreateTicketMap {

    @Autowired
    private EventService eventService;

    public Ticket toEntity(CreateTicketDTO dto) {
        Ticket ticket = new Ticket();

        ticket.setCustomerName(dto.getCustomerName());
        ticket.setCpf(dto.getCpf());
        ticket.setCustomerMail(dto.getCustomerMail());

        EventDTO event = eventService.getEventFromId(dto.getEventId());
        ticket.setEvent(event);

        if(dto.getBrlamount() != null){
            ticket.setBrlamount(dto.getBrlamount());
        }
        if(dto.getUsdamount() != null) {
            ticket.setUsdamount(dto.getUsdamount());
        }

        ticket.setStatus(dto.getStatus());

        System.out.printf("Ticket final para persistir: " + ticket);

        return ticket;
    }
}
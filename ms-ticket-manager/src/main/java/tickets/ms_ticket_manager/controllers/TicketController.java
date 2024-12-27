package tickets.ms_ticket_manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tickets.ms_ticket_manager.entities.DTOs.CreateTicketDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody CreateTicketDTO createTicketDTO){
        Ticket createdTicket = ticketService.createTicket(createTicketDTO);

        return ResponseEntity.ok(createdTicket);


    }


}

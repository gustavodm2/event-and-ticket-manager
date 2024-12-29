package tickets.ms_ticket_manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tickets.ms_ticket_manager.entities.DTOs.CreateTicketDTO;
import tickets.ms_ticket_manager.entities.DTOs.UpdateTicketDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    private String id;
    private UpdateTicketDTO dto;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody CreateTicketDTO createTicketDTO){
        Ticket createdTicket = ticketService.createTicket(createTicketDTO);

        return ResponseEntity.ok(createdTicket);
    }


    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(){
        List<Ticket> tickets = ticketService.getAllTickets();

        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id){
        Ticket ticket = ticketService.getTicketById(id);

        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String id, @RequestBody UpdateTicketDTO dto){
        Ticket updatedTicket = ticketService.updateTicket(dto, id);

        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable String id){
        Ticket deletedTicket = ticketService.deleteTicket(id);

        return ResponseEntity.ok(deletedTicket);
    }


}

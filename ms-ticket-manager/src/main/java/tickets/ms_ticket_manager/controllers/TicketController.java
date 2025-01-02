package tickets.ms_ticket_manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tickets.ms_ticket_manager.entities.DTOs.CheckTicketByEventResponse;
import tickets.ms_ticket_manager.entities.DTOs.CreateTicketDTO;
import tickets.ms_ticket_manager.entities.DTOs.UpdateTicketDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.services.TicketService;

import java.util.List;

@RestController
@RequestMapping
public class TicketController {

    @Autowired
    private TicketService ticketService;
    private String id;
    private UpdateTicketDTO dto;

    @PostMapping("/create-ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody CreateTicketDTO createTicketDTO){
        Ticket createdTicket = ticketService.createTicket(createTicketDTO);

        return ResponseEntity.ok(createdTicket);
    }


    @GetMapping("/get-all-tickets")
    public ResponseEntity<List<Ticket>> getAllTickets(){
        List<Ticket> tickets = ticketService.getAllTickets();

        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/check-tickets-by-event/{eventId}")
    public ResponseEntity<CheckTicketByEventResponse> getTicketsByEventResponse(@PathVariable String eventId){
        CheckTicketByEventResponse checkTicketByEventResponse = ticketService.getTicketsByEventResponse(eventId);

        return ResponseEntity.ok(checkTicketByEventResponse);
    }

    @GetMapping("/get-tickets-by-event/{eventId}")
    public ResponseEntity<List<Ticket>> getTicketsByEventId(@PathVariable String eventId){
        List<Ticket> tickets = ticketService.getTicketsByEventId(eventId);

        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/get-ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id){
        Ticket ticket = ticketService.getTicketById(id);

        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/get-ticket-by-cpf/{cpf}")
    public ResponseEntity<List<Ticket>> getTicketsByCpf(@PathVariable String cpf){
        List<Ticket> tickets = ticketService.getTicketByCpf(cpf);

        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/update-ticket/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String id, @RequestBody UpdateTicketDTO dto){
        Ticket updatedTicket = ticketService.updateTicket(dto, id);

        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/cancel-ticket/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable String id){
        Ticket deletedTicket = ticketService.deleteTicket(id);

        return ResponseEntity.ok(deletedTicket);
    }


}

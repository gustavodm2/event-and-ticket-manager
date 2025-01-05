package tickets.ms_ticket_manager.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
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

    @Operation(summary = "Create a new ticket", description = "Creates a new ticket and returns the created ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create-ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody CreateTicketDTO createTicketDTO){
        Ticket createdTicket = ticketService.createTicket(createTicketDTO);

        return ResponseEntity.ok(createdTicket);
    }

    @Operation(summary = "Get all tickets", description = "Fetches all tickets from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-all-tickets")
    public ResponseEntity<List<Ticket>> getAllTickets(){
        List<Ticket> tickets = ticketService.getAllTickets();

        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Check tickets by event", description = "Checks if there are tickets for a specific event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets checked successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/check-tickets-by-event/{eventId}")
    public ResponseEntity<CheckTicketByEventResponse> getTicketsByEventResponse(@PathVariable String eventId){
        CheckTicketByEventResponse checkTicketByEventResponse = ticketService.getTicketsByEventResponse(eventId);

        return ResponseEntity.ok(checkTicketByEventResponse);
    }

    @Operation(summary = "Get tickets by event ID", description = "Fetches tickets associated with a specific event ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-tickets-by-event/{eventId}")
    public ResponseEntity<List<Ticket>> getTicketsByEventId(@PathVariable String eventId){
        List<Ticket> tickets = ticketService.getTicketsByEventId(eventId);

        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Get ticket by ID", description = "Fetches a ticket by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Ticket not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id){
        Ticket ticket = ticketService.getTicketById(id);

        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "Get tickets by CPF", description = "Fetches tickets associated with a specific CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Tickets not found for the given CPF"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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

    @DeleteMapping("/cancel-ticket-by-cpf/{cpf}")
    public ResponseEntity<List<Ticket>> deleteTicketByCpf(@PathVariable String cpf){
        List<Ticket> deletedTickets = ticketService.deleteTicketByCpf(cpf);

        return ResponseEntity.ok(deletedTickets);
    }


}

package tickets.ms_ticket_manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import tickets.ms_ticket_manager.controllers.TicketController;
import tickets.ms_ticket_manager.entities.DTOs.CheckTicketByEventResponse;
import tickets.ms_ticket_manager.entities.DTOs.CreateTicketDTO;
import tickets.ms_ticket_manager.entities.DTOs.UpdateTicketDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.services.TicketService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketControllerTests {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket() {
        CreateTicketDTO createTicketDTO = new CreateTicketDTO();
        Ticket ticket = new Ticket();

        when(ticketService.createTicket(createTicketDTO)).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.createTicket(createTicketDTO);

        verify(ticketService, times(1)).createTicket(createTicketDTO);
        assertEquals(ticket, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetAllTickets() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());

        when(ticketService.getAllTickets()).thenReturn(tickets);

        ResponseEntity<List<Ticket>> response = ticketController.getAllTickets();

        verify(ticketService, times(1)).getAllTickets();
        assertEquals(tickets, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetTicketsByEventResponse() {
        String eventId = "1";
        CheckTicketByEventResponse checkTicketByEventResponse = new CheckTicketByEventResponse();

        when(ticketService.getTicketsByEventResponse(eventId)).thenReturn(checkTicketByEventResponse);

        ResponseEntity<CheckTicketByEventResponse> response = ticketController.getTicketsByEventResponse(eventId);

        verify(ticketService, times(1)).getTicketsByEventResponse(eventId);
        assertEquals(checkTicketByEventResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetTicketsByEventId() {
        String eventId = "1";
        List<Ticket> tickets = Arrays.asList(new Ticket());

        when(ticketService.getTicketsByEventId(eventId)).thenReturn(tickets);

        ResponseEntity<List<Ticket>> response = ticketController.getTicketsByEventId(eventId);

        verify(ticketService, times(1)).getTicketsByEventId(eventId);
        assertEquals(tickets, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetTicketById() {
        String id = "1";
        Ticket ticket = new Ticket();

        when(ticketService.getTicketById(id)).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.getTicketById(id);

        verify(ticketService, times(1)).getTicketById(id);
        assertEquals(ticket, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetTicketsByCpf() {
        String cpf = "123456789";
        List<Ticket> tickets = Arrays.asList(new Ticket());

        when(ticketService.getTicketByCpf(cpf)).thenReturn(tickets);

        ResponseEntity<List<Ticket>> response = ticketController.getTicketsByCpf(cpf);

        verify(ticketService, times(1)).getTicketByCpf(cpf);
        assertEquals(tickets, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testUpdateTicket() {
        String id = "1";
        UpdateTicketDTO dto = new UpdateTicketDTO();
        Ticket ticket = new Ticket();

        when(ticketService.updateTicket(dto, id)).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.updateTicket(id, dto);

        verify(ticketService, times(1)).updateTicket(dto, id);
        assertEquals(ticket, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteTicket() {
        String id = "1";
        Ticket ticket = new Ticket();

        when(ticketService.deleteTicket(id)).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.deleteTicket(id);

        verify(ticketService, times(1)).deleteTicket(id);
        assertEquals(ticket, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteTicketByCpf() {
        String cpf = "123456789";
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());

        when(ticketService.deleteTicketByCpf(cpf)).thenReturn(tickets);

        ResponseEntity<List<Ticket>> response = ticketController.deleteTicketByCpf(cpf);

        verify(ticketService, times(1)).deleteTicketByCpf(cpf);
        assertEquals(tickets, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }


}

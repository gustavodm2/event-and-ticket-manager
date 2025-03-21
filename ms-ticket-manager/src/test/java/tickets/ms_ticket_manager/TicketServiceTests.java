package tickets.ms_ticket_manager;


import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tickets.ms_ticket_manager.clients.EventClient;
import tickets.ms_ticket_manager.entities.DTOs.*;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.mappers.CreateTicketMap;
import tickets.ms_ticket_manager.mappers.UpdateTicketMap;
import tickets.ms_ticket_manager.producers.TicketProducer;
import tickets.ms_ticket_manager.repositories.TicketRepository;
import tickets.ms_ticket_manager.services.TicketService;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTests {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private CreateTicketMap createTicketMap;

    @Mock
    private UpdateTicketMap updateTicketMap;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TicketProducer ticketProducer;

    @Mock
    private EventClient eventClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket() {
        CreateTicketDTO dto = new CreateTicketDTO();
        Ticket ticket = new Ticket();

        when(createTicketMap.toEntity(dto)).thenReturn(ticket);
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.createTicket(dto);

        verify(ticketProducer, times(1)).publishMessageEmail(ticket);
        verify(ticketRepository, times(1)).save(ticket);
        assertEquals(ticket, result);
    }

    @Test
    void testGetAllTickets() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> result = ticketService.getAllTickets();

        verify(ticketRepository, times(1)).findAll();
        assertEquals(tickets, result);
    }

    @Test
    void testGetTicketsByEventResponse() {
        String eventId = "1";
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventId);
        List<Ticket> tickets = Arrays.asList(new Ticket());

        when(eventClient.getEventById(eventId)).thenReturn(eventDTO);
        when(ticketRepository.findByEventId(eventId)).thenReturn(tickets);

        CheckTicketByEventResponse response = ticketService.getTicketsByEventResponse(eventId);

        assertNotNull(response);
        assertTrue(response.isHasTickets());
        verify(eventClient, times(1)).getEventById(eventId);
        verify(ticketRepository, times(1)).findByEventId(eventId);
    }

    @Test
    void testGetTicketsByEventId() {
        String eventId = "1";
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventId);
        List<Ticket> tickets = Arrays.asList(new Ticket());

        when(eventClient.getEventById(eventId)).thenReturn(eventDTO);
        when(ticketRepository.findByEventId(eventId)).thenReturn(tickets);

        List<Ticket> result = ticketService.getTicketsByEventId(eventId);

        verify(eventClient, times(1)).getEventById(eventId);
        verify(ticketRepository, times(1)).findByEventId(eventId);
        assertEquals(tickets, result);
    }

    @Test
    void testGetTicketById() {
        String id = "1";
        Ticket ticket = new Ticket();

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.getTicketById(id);

        verify(ticketRepository, times(1)).findById(id);
        assertEquals(ticket, result);
    }

    @Test
    void testGetTicketByIdNotFound() {
        String id = "1";

        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ticketService.getTicketById(id));
        verify(ticketRepository, times(1)).findById(id);
    }


    @Test
    void testUpdateTicket() {
        String id = "1";
        UpdateTicketDTO dto = new UpdateTicketDTO();
        Ticket ticket = new Ticket();

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.updateTicket(dto, id);

        verify(updateTicketMap, times(1)).toEntity(dto, ticket);
        verify(ticketRepository, times(1)).save(ticket);
        assertEquals(ticket, result);
    }

    @Test
    void testUpdateTicketNotFound() {
        String id = "1";
        UpdateTicketDTO dto = new UpdateTicketDTO();

        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ticketService.updateTicket(dto, id));
        verify(ticketRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteTicket() {
        String id = "1";
        Ticket ticket = new Ticket();

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.deleteTicket(id);

        verify(ticketRepository, times(1)).findById(id);
        verify(ticketRepository, times(1)).save(ticket);
        assertEquals("INACTIVE", ticket.getStatus());
        assertEquals(ticket, result);
    }

    @Test
    void testDeleteTicketNotFound() {
        String id = "1";

        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ticketService.deleteTicket(id));
        verify(ticketRepository, times(1)).findById(id);
    }

    @Test
    void testGetTicketByCpf() {
        String cpf = "123456789";
        List<Ticket> tickets = Arrays.asList(new Ticket());

        when(ticketRepository.findTicketsByCpf(cpf)).thenReturn(tickets);

        List<Ticket> result = ticketService.getTicketByCpf(cpf);

        verify(ticketRepository, times(1)).findTicketsByCpf(cpf);
        assertEquals(tickets, result);
    }

    @Test
    void testGetTicketByCpfNotFound() {
        String cpf = "123456789";

        when(ticketRepository.findTicketsByCpf(cpf)).thenReturn(Collections.emptyList());

        assertThrows(EntityNotFoundException.class, () -> ticketService.getTicketByCpf(cpf));
        verify(ticketRepository, times(1)).findTicketsByCpf(cpf);
    }

    @Test
    void testDeleteTicketByCpf() {
        String cpf = "123456789";
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());

        when(ticketRepository.findTicketsByCpf(cpf)).thenReturn(tickets);
        when(ticketRepository.saveAll(tickets)).thenReturn(tickets);

        List<Ticket> result = ticketService.deleteTicketByCpf(cpf);

        verify(ticketRepository, times(1)).findTicketsByCpf(cpf);
        verify(ticketRepository, times(1)).saveAll(tickets);
        tickets.forEach(ticket -> assertEquals("INACTIVE", ticket.getStatus()));
        assertEquals(tickets, result);
    }

    @Test
    void testDeleteTicketByCpfNotFound() {
        String cpf = "123456789";

        when(ticketRepository.findTicketsByCpf(cpf)).thenReturn(Collections.emptyList());

        assertThrows(EntityNotFoundException.class, () -> ticketService.deleteTicketByCpf(cpf));
        verify(ticketRepository, times(1)).findTicketsByCpf(cpf);
    }


}

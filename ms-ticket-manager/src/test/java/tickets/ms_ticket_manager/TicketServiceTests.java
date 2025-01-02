package tickets.ms_ticket_manager;


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


}

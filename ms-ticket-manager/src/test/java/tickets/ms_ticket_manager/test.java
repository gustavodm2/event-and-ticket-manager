package tickets.ms_ticket_manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tickets.ms_ticket_manager.entities.DTOs.CreateTicketDTO;
import tickets.ms_ticket_manager.entities.Ticket;
import tickets.ms_ticket_manager.mappers.CreateTicketMap;


    @SpringBootTest
    public class test {

        @Autowired
        private CreateTicketMap createTicketMap;

        @Test
        public void testMapping() {
            CreateTicketDTO dto = new CreateTicketDTO();
            dto.setCustomerName("Test Name");
            dto.setCpf("123456789");
            dto.setCustomerMail("test@example.com");
            dto.setEventId("6776d5147485c47f23fbbf19");
            dto.setBrlamount("100.50");
            dto.setUsdamount("20.30");
            dto.setStatus("ACTIVE");

            Ticket ticket = createTicketMap.toEntity(dto);
            System.out.println(ticket);
        }
    }




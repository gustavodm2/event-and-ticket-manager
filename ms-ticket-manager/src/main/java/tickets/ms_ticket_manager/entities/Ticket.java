package tickets.ms_ticket_manager.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tickets.ms_ticket_manager.entities.DTOs.EventDTO;

@Document(collection = "tickets")
@Data
public class Ticket {

    @Id
    private String ticketId;
    private String customerName;
    private String cpf;
    private String customerMail;
    private EventDTO event;
    private String brlamount;
    private String usdamount;
    private String status;


}

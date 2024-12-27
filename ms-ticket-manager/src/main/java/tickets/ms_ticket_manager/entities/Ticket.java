package tickets.ms_ticket_manager.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tickets.ms_ticket_manager.entities.DTOs.EventDTO;

@Document(collection = "db_ticket")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    private String ticketId;
    private String customerName;
    private String cpf;
    private String customerMail;
    private EventDTO event;
    private String BRLAmount;
    private String USDAmount;
    private String status;

}

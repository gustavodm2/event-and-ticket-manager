package tickets.ms_ticket_manager.entities.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketDTO {

    private String customerName;
    private String cpf;
    private String customerMail;
    private String eventId;
    private String eventName;
    private String BRLAmount;
    private String USDAmount;
    private String status;

}

package tickets.ms_ticket_manager.entities.DTOs;

import lombok.*;

@Data
public class CreateTicketDTO {

    private String customerName;
    private String cpf;
    private String customerMail;
    private String eventId;
    private String brlamount;
    private String usdamount;
    private String status;
}

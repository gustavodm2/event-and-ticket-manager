package events.entities.DTOs;

import events.entities.Event;
import lombok.Data;

@Data
public class TicketDTO {

    private String ticketId;
    private String customerName;
    private String cpf;
    private String customerMail;
    private Event event;
    private String BRLAmount;
    private String USDAmount;
    private String status;

    private boolean isDeleted;


}

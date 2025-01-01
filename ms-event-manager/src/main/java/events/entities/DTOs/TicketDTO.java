package events.entities.DTOs;

import events.entities.Event;
import org.springframework.data.annotation.Id;

public class TicketDTO {

    @Id
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

package tickets.ms_ticket_manager.entities.DTOs;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class EventDTO {

    private String eventName;
    private String dateTime;
    private AddressDTO cep;


}

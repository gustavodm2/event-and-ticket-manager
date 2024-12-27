package tickets.ms_ticket_manager.entities.DTOs;

import lombok.*;

@Data
public class EventDTO {

    private String eventName;
    private String dateTime;
    private AddressDTO cep;


}

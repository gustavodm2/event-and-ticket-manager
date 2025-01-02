package tickets.ms_ticket_manager.entities.DTOs;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
public class EventDTO {

    @Id
    private String id;
    private String eventName;
    private String dateTime;
    private AddressDTO cep;

    private boolean isDeleted;


}

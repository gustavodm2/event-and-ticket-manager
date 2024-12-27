package tickets.ms_ticket_manager.entities.DTOs;

import lombok.*;

@Data
public class EventDTO {

    private String eventName;
    private String dateTime;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;


}

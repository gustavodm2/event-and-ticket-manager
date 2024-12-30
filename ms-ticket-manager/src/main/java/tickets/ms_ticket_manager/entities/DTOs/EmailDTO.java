package tickets.ms_ticket_manager.entities.DTOs;

import lombok.Data;

@Data
public class EmailDTO {


    private String emailTo;
    private String subject;
    private String text;

}

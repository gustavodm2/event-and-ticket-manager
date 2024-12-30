package tickets.ms_ticket_manager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import tickets.ms_ticket_manager.enums.StatusEmail;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Email implements Serializable {

    @Id
    private UUID emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}

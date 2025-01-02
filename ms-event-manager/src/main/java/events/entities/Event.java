package events.entities;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Event {

    @Id
    private String id;
    private String eventName;
    private String dateTime;
    private Address cep;

    private boolean isDeleted;
}

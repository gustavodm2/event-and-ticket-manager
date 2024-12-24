package events.entities;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_events")
@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Event {

    @Id
    private Long id;
    private String eventName;
    private String dateTime;
    private Address cep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Address getCep() {
        return cep;
    }

    public void setCep(Address cep) {
        this.cep = cep;
    }
}

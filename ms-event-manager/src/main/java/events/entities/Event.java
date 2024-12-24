package events.entities;

import jakarta.persistence.Entity;
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
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;


}

package events.entities.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDTO {

    private String eventName;
    private String dateTime;
    private String cep;

}

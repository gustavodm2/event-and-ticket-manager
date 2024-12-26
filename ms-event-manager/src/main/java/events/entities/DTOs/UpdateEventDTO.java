package events.entities.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventDTO {

    public String eventName;
    public String cep;


}

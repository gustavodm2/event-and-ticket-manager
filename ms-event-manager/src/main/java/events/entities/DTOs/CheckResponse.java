package events.entities.DTOs;

import lombok.Data;

@Data
public class CheckResponse {

    private String eventId;
    private boolean hasTickets;

}

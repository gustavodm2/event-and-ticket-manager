package events.clients;

import events.entities.DTOs.TicketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-ticket-manager", url = "localhost:8081")
public interface TicketClient {

    @GetMapping("/check-tickets-by-event/{eventId}")
    List<TicketDTO> getTicketsById(String eventId);


}

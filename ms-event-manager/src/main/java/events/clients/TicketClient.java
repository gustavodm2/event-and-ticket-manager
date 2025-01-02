package events.clients;

import events.entities.DTOs.CheckResponse;
import events.entities.DTOs.TicketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-ticket-manager", url = "http://ms-ticket-client:8081/")
public interface TicketClient {

    @GetMapping("/check-tickets-by-event/{eventId}")
    CheckResponse getTicketsResponse(@PathVariable String eventId);

    @GetMapping("/get-tickets-by-event/{eventId}")
    List<TicketDTO> getTicketsByEventId(@PathVariable String eventId);


}

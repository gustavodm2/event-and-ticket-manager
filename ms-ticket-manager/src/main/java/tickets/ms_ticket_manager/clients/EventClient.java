package tickets.ms_ticket_manager.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tickets.ms_ticket_manager.entities.DTOs.EventDTO;

@FeignClient(name = "ms-event-manager", url = "https://localhost:8080")
public interface EventClient {

    @GetMapping("/events/{id}")
    EventDTO getEventById(@PathVariable String id);

}

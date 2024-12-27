package tickets.ms_ticket_manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.ms_ticket_manager.clients.EventClient;
import tickets.ms_ticket_manager.entities.DTOs.EventDTO;

@Service
public class EventService {

    @Autowired
    private EventClient eventClient;

    public EventDTO getEventFromId(String id){
        return eventClient.getEventById(id);
    }

}

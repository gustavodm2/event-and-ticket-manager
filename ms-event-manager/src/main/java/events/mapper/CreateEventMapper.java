package events.mapper;

import events.entities.Address;
import events.entities.DTOs.CreateEventDTO;
import events.entities.Event;
import events.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateEventMapper {

    @Autowired
    private CepService cepService;

    public Event toEntity(CreateEventDTO dto) {
        Event event = new Event();
        event.setEventName(dto.getEventName());
        event.setDateTime(dto.getDateTime());
        Address address = cepService.getAddressFromCep(dto.getCep());
        event.setCep(address);
        return event;
    }
}

package events.mapper;

import events.entities.Address;
import events.entities.DTOs.CreateEventDTO;
import events.entities.Event;
import events.services.CepService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

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

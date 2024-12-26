package events.mapper;

import events.entities.Address;
import events.entities.DTOs.UpdateEventDTO;
import events.entities.Event;
import events.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateEventMapper {

    @Autowired
    CepService cepService;

    public void updateEntity(UpdateEventDTO dto, Event event) {
        if (dto.getEventName() != null) {
            event.setEventName(dto.getEventName());
        }
        if (dto.getCep() != null) {
            Address address = cepService.getAddressFromCep(dto.getCep());
            event.setCep(address);
        }
    }
}

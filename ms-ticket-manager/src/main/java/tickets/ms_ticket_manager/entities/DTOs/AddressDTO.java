package tickets.ms_ticket_manager.entities.DTOs;

import lombok.Data;

@Data
public class AddressDTO {
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}

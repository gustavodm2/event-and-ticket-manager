package events.entities;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String cep;
    private String logradouro;
    private String localidade;
    private String bairro;
    private String uf;


}

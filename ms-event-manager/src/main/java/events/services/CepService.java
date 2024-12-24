package events.services;

import events.clients.ViaCepClient;
import events.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    private final ViaCepClient viaCepClient;

    public CepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }


    public Address getAddressFromCep(String cep){
        return viaCepClient.getAddressFromCep(cep);
    }

}

package events.services;

import events.clients.ViaCepClient;
import events.entities.Address;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class CepService {

    private final ViaCepClient viaCepClient;

    public CepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

        public Address getAddressFromCep(String cep) {
            if (cep == null || !cep.matches("\\d{8}")) {
                throw new IllegalArgumentException("Invalid CEP: Must be exactly 8 numeric characters.");
            }

            try {
                return viaCepClient.getAddressFromCep(cep);
            } catch (FeignException.BadRequest e) {
                throw new IllegalArgumentException("Invalid CEP: The given CEP does not exist or is improperly formatted.");
            } catch (RestClientException e) {
                throw new RuntimeException("Error while connecting to ViaCEP service.", e);
            }
        }
    }

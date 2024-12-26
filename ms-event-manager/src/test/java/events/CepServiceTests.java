package events;

import events.entities.Address;
import events.services.CepService;
import events.clients.ViaCepClient;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CepServiceTest {

    private ViaCepClient viaCepClient;
    private CepService cepService;

    @BeforeEach
    void setUp() {
        viaCepClient = mock(ViaCepClient.class);
        cepService = new CepService(viaCepClient);
    }

    @Test
    void getAddressFromCep_ShouldReturnAddress_WhenValidCepIsProvided() {
        String validCep = "97700085";
        Address mockAddress = new Address("97700085", "Rua Jornalista Eudócio Pozo", "Santiago", "Centro", "RS");
        when(viaCepClient.getAddressFromCep(validCep)).thenReturn(mockAddress);

        Address result = cepService.getAddressFromCep(validCep);

        assertNotNull(result);
        assertEquals(mockAddress, result);
        verify(viaCepClient, times(1)).getAddressFromCep(validCep);
    }
}

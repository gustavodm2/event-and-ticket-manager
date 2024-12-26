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

    @Test
    void getAddressFromCep_ShouldThrowIllegalArgumentException_WhenCepIsInvalid() {
        String invalidCep = "123";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cepService.getAddressFromCep(invalidCep)
        );

        assertEquals("Invalid CEP: Must be exactly 8 numeric characters.", exception.getMessage());
        verify(viaCepClient, never()).getAddressFromCep(anyString());
    }

    @Test
    void getAddressFromCep_ShouldThrowIllegalArgumentException_WhenViaCepClientReturnsBadRequest() {
        String nonExistentCep = "87654321";
        when(viaCepClient.getAddressFromCep(nonExistentCep)).thenThrow(FeignException.BadRequest.class);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> cepService.getAddressFromCep(nonExistentCep)
        );

        assertEquals("Invalid CEP: The given CEP does not exist or is improperly formatted.", exception.getMessage());
        verify(viaCepClient, times(1)).getAddressFromCep(nonExistentCep);
    }

    @Test
    void getAddressFromCep_ShouldThrowRuntimeException_WhenConnectionFails() {
        String validCep = "97700085";
        when(viaCepClient.getAddressFromCep(validCep)).thenThrow(RestClientException.class);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> cepService.getAddressFromCep(validCep)
        );

        assertTrue(exception.getMessage().contains("Error while connecting to ViaCEP service."));
        verify(viaCepClient, times(1)).getAddressFromCep(validCep);
    }
}

package events.controllers;

import events.entities.Address;
import events.services.CepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }


    @GetMapping("/get-address/{cep}")
    public Address getAddress(@PathVariable String cep){
        return cepService.getAddressFromCep(cep);
    }


}

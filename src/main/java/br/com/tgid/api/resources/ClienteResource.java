package br.com.tgid.api.resources;

import br.com.tgid.api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

    private final ClienteService service;


    @Autowired
    public ClienteResource(ClienteService service) {
        this.service = service;
    }
}

package br.com.tgid.api.resources;

import br.com.tgid.api.dto.TransacaoDTO;

import br.com.tgid.api.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transacao")
public class TransacaoResource {

    private final TransacaoService transacaoService;

    public TransacaoResource(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> realizarTransacao(@RequestBody TransacaoDTO transacaoDTO, @RequestParam(name = "id_hook") UUID uniqueUrl) {
        return ResponseEntity.status(HttpStatus.OK).body(transacaoService.realizarTransacao(transacaoDTO, uniqueUrl));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(transacaoService.findAll());
    }
}

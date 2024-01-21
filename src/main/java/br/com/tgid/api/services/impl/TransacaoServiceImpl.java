package br.com.tgid.api.services.impl;

import br.com.tgid.api.domain.Transacao;
import br.com.tgid.api.dto.TransacaoDTO;
import br.com.tgid.api.mappers.TransacaoMapper;
import br.com.tgid.api.repositories.TransacaoRepository;
import br.com.tgid.api.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final TransacaoMapper transacaoMapper;

    @Autowired
    public TransacaoServiceImpl(TransacaoMapper transacaoMapper, TransacaoRepository transacaoRepository) {
        this.transacaoMapper = transacaoMapper;
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    public List<TransacaoDTO> findAll() {
        return transacaoRepository.findAll().stream().map(transacaoMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public TransacaoDTO realizarTransacao(TransacaoDTO transacaoDTO, UUID uniqueUrl) {
        Transacao save = transacaoRepository.save(transacaoMapper.dtoToEntity(transacaoDTO));
        TransacaoDTO transacao = transacaoMapper.entityToDto(save);
        transacaoDTO.setId(save.getId());
        transacaoDTO.setTaxa(save.getTaxa());

        WebClient.builder().baseUrl("https://webhook.site").build()
                .post()
                .uri("https://webhook.site/" + uniqueUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN)
                .bodyValue(transacaoDTO)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(responseText -> {
                    System.out.println("Resposta do servidor: " + responseText);
                });

        return transacao;
    }

    public void transacaoRequest(TransacaoDTO transacaoDTO) {
        System.out.println("transacao" + transacaoDTO);
    }


    @Override
    public void enviarCallBackHook(TransacaoDTO transacaoDTO, String path) {
    }
}

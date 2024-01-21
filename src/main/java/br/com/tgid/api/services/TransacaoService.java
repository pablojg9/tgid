package br.com.tgid.api.services;

import br.com.tgid.api.dto.TransacaoDTO;

import java.util.List;
import java.util.UUID;


public interface TransacaoService {

    List<TransacaoDTO> findAll();

    TransacaoDTO realizarTransacao(TransacaoDTO transacao, UUID uniqueUrl);

    void enviarCallBackHook(TransacaoDTO transacaoDTO, String path);

}

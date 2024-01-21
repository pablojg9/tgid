package br.com.tgid.api.services;

import br.com.tgid.api.dto.ClienteDTO;

public interface ClienteService {

    ClienteDTO findByCpf(String cpf);
}

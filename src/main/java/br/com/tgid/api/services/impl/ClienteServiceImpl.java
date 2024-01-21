package br.com.tgid.api.services.impl;

import br.com.tgid.api.dto.ClienteDTO;
import br.com.tgid.api.exceptions.ObjectNotFoundException;
import br.com.tgid.api.mappers.ClienteMapper;
import br.com.tgid.api.repositories.ClienteRepository;
import br.com.tgid.api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClienteDTO findByCpf(String cpf) {
        return mapper.entityToDto(repository.findByCpf(cpf)
                .orElseThrow(() -> new ObjectNotFoundException("Cpf not found")));
    }
}

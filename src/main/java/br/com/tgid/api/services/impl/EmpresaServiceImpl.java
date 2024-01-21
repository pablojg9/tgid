package br.com.tgid.api.services.impl;

import br.com.tgid.api.dto.EmpresaDTO;
import br.com.tgid.api.exceptions.ObjectNotFoundException;
import br.com.tgid.api.mappers.EmpresaMapper;
import br.com.tgid.api.repositories.EmpresaRepository;
import br.com.tgid.api.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    @Autowired
    public EmpresaServiceImpl(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
    }

    @Override
    public EmpresaDTO findByCnpj(String cpnj) {
        return empresaMapper
                .entityToDto(empresaRepository
                .findByCnpj(cpnj).orElseThrow(() -> new ObjectNotFoundException("Cpnj Not found")));
    }

    @Override
    public List<EmpresaDTO> findAll() {
       return empresaRepository.findAll().stream().map(empresaMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Integer adicionarValorAoSaldoPorCnpj(String cnpj, Double valorAdicionado) {
        return empresaRepository.adicionarValorAoSaldoPorCnpj(cnpj, valorAdicionado);
    }
}

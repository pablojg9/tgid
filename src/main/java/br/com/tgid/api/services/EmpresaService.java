package br.com.tgid.api.services;

import br.com.tgid.api.dto.EmpresaDTO;

import java.util.List;


public interface EmpresaService {

    EmpresaDTO findByCnpj(String cpnj);

    List<EmpresaDTO> findAll();

    Integer adicionarValorAoSaldoPorCnpj(String cnpj, Double valorAdicionado);
}

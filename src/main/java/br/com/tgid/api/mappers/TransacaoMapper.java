package br.com.tgid.api.mappers;

import br.com.tgid.api.domain.Transacao;
import br.com.tgid.api.dto.ClienteDTO;
import br.com.tgid.api.dto.EmpresaDTO;
import br.com.tgid.api.dto.TransacaoDTO;
import br.com.tgid.api.exceptions.ObjectNotFoundException;
import br.com.tgid.api.services.ClienteService;
import br.com.tgid.api.services.EmpresaService;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        imports = {
            LocalDateTime.class
        }
)
public abstract class TransacaoMapper {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ClienteService clienteService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "valor", expression = "java(getValorAtualizaSaldoEmpresa(transacaoDTO.getValor(), transacaoDTO.getCnpj()))")
    @Mapping(target = "cnpj", expression = "java(getCpnj(transacaoDTO.getCnpj()))")
    @Mapping(target = "cpf", expression = "java(getCpf(transacaoDTO.getCpf()))")
    @Mapping(target = "taxa", expression = "java(getTaxa(transacaoDTO.getCnpj()))")
    @Mapping(target = "dateTime", expression = "java(LocalDateTime.now())")
    public abstract Transacao dtoToEntity(TransacaoDTO transacaoDTO);

    public abstract TransacaoDTO entityToDto(Transacao transacao);

    @Named("getValorAtualizaSaldoEmpresa")
    public Double getValorAtualizaSaldoEmpresa(Double valor, String cpnj) {
        if (valor > 0) {
            empresaService.adicionarValorAoSaldoPorCnpj(cpnj, valor);
            return valor;
        }
        return 0D;
    }

    @Named("getCpf")
    public String getCpf(String cpf) {
        ClienteDTO clienteDTO = Optional.ofNullable(clienteService.findByCpf(cpf)).
                orElseThrow(() -> new ObjectNotFoundException("Cpj not found"));
        return clienteDTO.getCpf();
    }

    @Named("getCpnj")
    public String getCpnj(String cpnj) {
        EmpresaDTO empresaDTO = Optional.ofNullable(empresaService.findByCnpj(cpnj))
                .orElseThrow(() -> new ObjectNotFoundException("Cpnj not found"));
        return empresaDTO.getCnpj();
    }

    @Named("getTaxa")
    public Double getTaxa(String cpnj) {
        EmpresaDTO empresaDTO = Optional.ofNullable(empresaService.findByCnpj(cpnj))
                .orElseThrow(() -> new ObjectNotFoundException("Cpnj not found"));
        return empresaDTO.getTaxa();
    }
}

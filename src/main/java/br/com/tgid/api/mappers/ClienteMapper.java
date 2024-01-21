package br.com.tgid.api.mappers;

import br.com.tgid.api.domain.Cliente;
import br.com.tgid.api.dto.ClienteDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true)
)
public interface ClienteMapper {

    ClienteDTO entityToDto(Cliente cliente);
    Cliente dtoToEntity(ClienteDTO clienteDTO);
}

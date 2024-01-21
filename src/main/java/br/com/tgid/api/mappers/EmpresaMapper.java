package br.com.tgid.api.mappers;

import br.com.tgid.api.domain.Empresa;
import br.com.tgid.api.dto.EmpresaDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        builder = @Builder(disableBuilder = true)
)
public interface EmpresaMapper {

    EmpresaDTO entityToDto(Empresa empresa);
    Empresa dtoToEntity(EmpresaDTO empresaDTO);
}

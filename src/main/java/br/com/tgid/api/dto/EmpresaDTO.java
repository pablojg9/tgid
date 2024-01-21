package br.com.tgid.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaDTO {

    private UUID id;
    private String nome;
    private String cnpj;
    private Double saldo;
    private Double taxa;
}

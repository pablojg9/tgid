package br.com.tgid.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransacaoDTO {

    @JsonProperty("transacao_id")
    private UUID id;
    private String cpf;
    private String cnpj;
    private Double valor;

    @JsonProperty("taxa_padrao")
    private Double taxa;
}

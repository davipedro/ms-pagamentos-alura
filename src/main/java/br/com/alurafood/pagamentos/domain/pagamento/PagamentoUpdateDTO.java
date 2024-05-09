package br.com.alurafood.pagamentos.domain.pagamento;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PagamentoUpdateDTO(
    BigDecimal valor,
    String nome,
    String numero,
    String expiracao,
    String codigo,
    Status status,
    Long formaDePagamentoId) {
    
}

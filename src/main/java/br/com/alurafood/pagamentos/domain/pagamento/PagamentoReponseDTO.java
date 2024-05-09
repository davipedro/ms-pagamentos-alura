package br.com.alurafood.pagamentos.domain.pagamento;

import java.math.BigDecimal;

public record PagamentoReponseDTO(
    BigDecimal valor,
    String nome,
    String numero,
    String expiracao,
    String codigo,
    Status status,
    Long pedidoId,
    Long formaDePagamentoId) {
    
}

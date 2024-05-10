package br.com.alurafood.pagamentos.domain.pagamento;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PagamentoRequestDTO{
    @NotNull
    @Positive
    @JsonAlias("valor")
    private BigDecimal valor;

    @NotBlank
    @Size(max = 100)
    @JsonAlias("nome")
    private  String nome;
    
    @NotBlank
    @Size(max = 19)
    @JsonAlias("numero")
    private  String numero;
    
    @NotBlank
    @Size(max = 7)
    @JsonAlias("expiracao")
    private  String expiracao;
    
    @NotBlank
    @Size(min = 3, max = 3)
    @JsonAlias("codigo")
    private  String codigo;
    
    @NotNull
    @JsonAlias("pedido_id")
    private  Long pedidoId;

    @NotNull
    @JsonAlias("forma_de_pagamento_id")
    private  Long formaDePagamentoId;
}

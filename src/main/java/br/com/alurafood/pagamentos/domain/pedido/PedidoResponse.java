package br.com.alurafood.pagamentos.domain.pedido;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoResponse {
    @JsonAlias("itens")
    private List<ItemDoPedido> itens;
}

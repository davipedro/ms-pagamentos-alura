package br.com.alurafood.pagamentos.infra.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alurafood.pagamentos.domain.pedido.PedidoResponse;

// @FeignClient("nome-do-servico")
@FeignClient("pedidos-ms" )
public interface PedidoClient {
    
    @RequestMapping(method = RequestMethod.PUT, value = "/pedidos/{id}/pago")
    void atualizaPagamento(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/pedidos/{id}")
    PedidoResponse obterDetalhesPedido(@PathVariable("id") Long id);
}
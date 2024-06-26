package br.com.alurafood.pagamentos.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alurafood.pagamentos.domain.pagamento.Pagamento;
import br.com.alurafood.pagamentos.domain.pagamento.PagamentoResponseDTO;
import br.com.alurafood.pagamentos.domain.pagamento.PagamentoUpdateDTO;
import br.com.alurafood.pagamentos.domain.pagamento.PagamentoRequestDTO;
import br.com.alurafood.pagamentos.domain.pagamento.Status;
import br.com.alurafood.pagamentos.domain.pedido.PedidoResponse;
import br.com.alurafood.pagamentos.infra.http.PedidoClient;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

@Service
public class PagamentoService {
    
    @Autowired
    private PedidoClient pedido;

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoResponseDTO> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoResponseDTO.class));
    }

    public PagamentoResponseDTO criarPagamento(PagamentoRequestDTO dados){
        var pagamento = modelMapper.map(dados, Pagamento.class);

        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoResponseDTO.class);
    }

    public PagamentoResponseDTO atualizarPagamento(Long id, PagamentoUpdateDTO dados){
        var pagamento = repository.findById(id).orElseThrow();

        if (dados.getValor() != null) pagamento.setValor(dados.getValor());
        
        if (dados.getNome() != null) pagamento.setNome(dados.getNome());
        
        if (dados.getNumero() != null) pagamento.setNumero(dados.getNumero());
        
        if (dados.getExpiracao() != null) pagamento.setExpiracao(dados.getExpiracao());
        
        if (dados.getCodigo() != null) pagamento.setCodigo(dados.getCodigo());
        
        if (dados.getStatus() != null && Status.isStatusValido(dados.getStatus())) pagamento.setStatus(dados.getStatus());
        
        if (dados.getFormaDePagamentoId() != null) pagamento.setFormaDePagamentoId(dados.getFormaDePagamentoId());
        
        pagamento = repository.save(pagamento);
        
        return modelMapper.map(pagamento, PagamentoResponseDTO.class);
    }
    
    public PagamentoResponseDTO obterPorId(@NotNull Long id) {
        Pagamento pagamento = repository.findById(id).orElseThrow();
        PagamentoResponseDTO dto = modelMapper.map(pagamento, PagamentoResponseDTO.class);

        PedidoResponse infoPedido = pedido.obterDetalhesPedido(id);

        dto.setItens(infoPedido.getItens());

        return dto;
    }
    
    public void excluirPagamento(Long id){
        repository.deleteById(id);
    }

    public void confirmarPagamento(Long id){
        var pagamento = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        pagamento.setStatus(Status.CONFIRMADO);
        repository.save(pagamento);
        pedido.atualizaPagamento(pagamento.getPedidoId());
    }

    public void alteraStatus(Long id) {
        var pagamento = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        pagamento.setStatus(Status.CONFIRMADO_SEM_INTEGRACAO);
        repository.save(pagamento);
    }

}

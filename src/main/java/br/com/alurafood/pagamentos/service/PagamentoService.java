package br.com.alurafood.pagamentos.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alurafood.pagamentos.domain.pagamento.Pagamento;
import br.com.alurafood.pagamentos.domain.pagamento.PagamentoReponseDTO;
import br.com.alurafood.pagamentos.domain.pagamento.PagamentoRequestDTO;
import br.com.alurafood.pagamentos.domain.pagamento.Status;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoReponseDTO> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoReponseDTO.class));
    }

    public PagamentoReponseDTO criarPagamento(PagamentoRequestDTO dados){
        var pagamento = modelMapper.map(dados, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoReponseDTO.class);
    }

    public PagamentoReponseDTO atualizarPagamento(Long id, PagamentoRequestDTO dados){
        var pagamento = modelMapper.map(dados, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        
        return modelMapper.map(pagamento, PagamentoReponseDTO.class);
    }

    public void excluirPagamento(Long id){
        repository.deleteById(id);
    }
}

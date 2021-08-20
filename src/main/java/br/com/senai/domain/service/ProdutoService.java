package br.com.senai.domain.service;

import br.com.senai.domain.model.Produto;
import br.com.senai.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto cadastrar(Produto produto){
        produto.setValor_total_em_estoque(produto.getValor_unitario()  * produto.getQuantidade());
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto editar(Produto produto){
        produto.setValor_total_em_estoque(produto.getValor_unitario()  * produto.getQuantidade());
        return produtoRepository.save(produto);
    }

    @Transactional
    public void excluir(Long produtoId){
        produtoRepository.deleteById(produtoId);
    }
}

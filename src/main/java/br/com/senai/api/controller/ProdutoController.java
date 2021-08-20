package br.com.senai.api.controller;

import br.com.senai.api.assembler.ProdutoAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.ProdutoDTO;
import br.com.senai.api.model.input.ProdutoInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.Produto;
import br.com.senai.domain.repository.ProdutoRepository;
import br.com.senai.domain.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;
    private ProdutoAssembler produtoAssembler;

    @PostMapping
    public ProdutoDTO cadastrar(@Valid @RequestBody ProdutoInputDTO produtoInputDTO){
        Produto newProduto = produtoAssembler.toEntity(produtoInputDTO);
        Produto produto = produtoService.cadastrar(newProduto);

        return produtoAssembler.toModel(produto);
    }

    @GetMapping
    public List<ProdutoDTO> listar(){
        return produtoAssembler.toCollectionModel(produtoRepository.findAll());
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> editar(
            @Valid @PathVariable Long produtoId,
            @RequestBody Produto produto
    ){
        if(!produtoRepository.existsById(produtoId)){
            return ResponseEntity.notFound().build();
        }

        produto.setId(produtoId);
        produto = produtoService.cadastrar(produto);
        produto = produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Produto> remover(@PathVariable Long produtoId){
        if(!produtoRepository.existsById(produtoId)){
            return ResponseEntity.notFound().build();
        }

        produtoService.excluir(produtoId);

        return ResponseEntity.noContent().build();
    }
}

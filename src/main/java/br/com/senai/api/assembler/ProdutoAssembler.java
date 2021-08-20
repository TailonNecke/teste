package br.com.senai.api.assembler;

import br.com.senai.api.model.ProdutoDTO;
import br.com.senai.api.model.input.ProdutoInputDTO;
import br.com.senai.domain.model.Produto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProdutoAssembler {

    ModelMapper modelMapper;

    public ProdutoDTO toModel(Produto produto){
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public Produto toEntity(ProdutoInputDTO produtoInputDTO){
        return modelMapper.map(produtoInputDTO, Produto.class);
    }

    public List<ProdutoDTO> toCollectionModel(List<Produto> produtos){
        return produtos.stream().map(this::toModel)
                .collect(Collectors.toList());
    }
}

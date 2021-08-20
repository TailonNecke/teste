package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ProdutoInputDTO {



    private String produto;
    private long quantidade;
    private double valor_unitario;
    private double valor_total_em_estoque;
}

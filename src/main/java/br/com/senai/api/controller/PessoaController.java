package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.assembler.PerfilAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.input.PessoaInputDTO;
import br.com.senai.api.model.input.PerfilInputDTO;
import br.com.senai.domain.model.Perfil;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;
    private PerfilAssembler perfilAssembler;

    @GetMapping
    public List<PessoaDTO> listar(){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }

    @PostMapping
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa newPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        newPessoa.getPerfil().setSenha(
                new BCryptPasswordEncoder()
                        .encode(pessoaInputDTO.getPerfil().getSenha()));

        Pessoa pessoa = pessoaService.cadastrar(newPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    @PostMapping("/perfil")
    public PessoaDTO cadPerfil(@Valid @RequestBody PerfilInputDTO perfilInputDTO){
        Perfil perfil = perfilAssembler.toEntity(perfilInputDTO);
        Pessoa newPessoa = new Pessoa();
        newPessoa.setPerfil(perfil);
        newPessoa.setNome("Sem nome");

        newPessoa.getPerfil().setSenha(
                new BCryptPasswordEncoder()
                        .encode(perfilInputDTO.getSenha()));

        Pessoa pessoa = pessoaService.cadastrar(newPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> editar(
            @Valid @PathVariable Long pessoaId,
            @RequestBody Pessoa pessoa
    ){
        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        pessoa.setId(pessoaId);
        pessoa.getPerfil().setId(pessoa.getId());
        pessoa.getPerfil().setSenha(
                new BCryptPasswordEncoder()
                        .encode(pessoa.getPerfil().getSenha()));
        pessoa = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@PathVariable Long pessoaId){
        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        pessoaService.excluir(pessoaId);

        return ResponseEntity.noContent().build();
    }
}

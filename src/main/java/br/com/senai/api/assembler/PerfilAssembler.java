package br.com.senai.api.assembler;

import br.com.senai.api.model.PerfilDTO;
import br.com.senai.api.model.input.PerfilInputDTO;
import br.com.senai.domain.model.Perfil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PerfilAssembler {
    ModelMapper modelMapper;

    public PerfilDTO toModel(Perfil perfil){

        return modelMapper.map(perfil, PerfilDTO.class);
    }

    public Perfil toEntity(PerfilInputDTO perfilInputDTO){

        return modelMapper.map(perfilInputDTO, Perfil.class);
    }
}

package br.com.senai.domain.model;

import br.com.senai.api.model.PerfilDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private String jwt;
    private PerfilDTO perfil;

}

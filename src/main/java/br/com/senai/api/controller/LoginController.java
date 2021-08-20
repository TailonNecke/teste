package br.com.senai.api.controller;

import br.com.senai.api.assembler.PerfilAssembler;
import br.com.senai.api.model.input.PerfilInputDTO;
import br.com.senai.domain.model.AuthenticationResponse;
import br.com.senai.domain.model.Perfil;
import br.com.senai.security.ImplementsUserDetailsService;
import br.com.senai.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private ImplementsUserDetailsService implementsUserDetailsService;
    private JWTUtil jwtUtil;
    private PerfilAssembler perfilAssembler;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody PerfilInputDTO perfilInputDTO) throws Exception{
        Perfil perfil = perfilAssembler.toEntity(perfilInputDTO);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            perfil.getUsername(), perfil.getPassword())
            );
        } catch (BadCredentialsException ex){
            throw new Exception("Usuário ou senha inválidos.", ex);
        }

        final UserDetails userDetails = implementsUserDetailsService
                .loadUserByUsername(perfil.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, perfilAssembler.toModel(perfil)));
    }

}

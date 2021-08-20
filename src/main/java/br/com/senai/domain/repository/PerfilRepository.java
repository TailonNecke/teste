package br.com.senai.domain.repository;

import br.com.senai.domain.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query("SELECT u FROM Perfil u WHERE u.email = ?1")
    Perfil findByEmail(String email);

}

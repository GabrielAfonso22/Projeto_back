package br.com.malldelivery.lojista.repository;

import br.com.malldelivery.lojista.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "select * from usuario u where u.username = ?1 and u.password = ?2 limit 1", nativeQuery = true)
    Optional<Usuario> findByUsernameAndPassword(String username, String password);

    @Query(value = "select * from usuario u where u.username = ?1 limit 1", nativeQuery = true)
    Optional<Usuario> findByUsername(String username);
}

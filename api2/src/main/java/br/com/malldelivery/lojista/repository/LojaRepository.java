package br.com.malldelivery.lojista.repository;


import br.com.malldelivery.lojista.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {
    @Query(value = "select * from loja l where l.cnpj = :cnpj", nativeQuery = true)
    Optional<Loja> findByCnpj(@Param("cnpj") String cnpj);
}

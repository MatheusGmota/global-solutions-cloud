package br.com.ecoalert.repositories;

import br.com.ecoalert.domain.entities.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

    Optional<Localizacao> findByCidade(String cidade);

    Optional<Localizacao> findByCidadeAndEstadoAndLatitudeAndLongitude(String cidade, String estado, String latitude, String longitude);
}

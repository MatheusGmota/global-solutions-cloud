package br.com.ecoalert.repositories;

import br.com.ecoalert.domain.entities.Localizacao;
import br.com.ecoalert.domain.entities.StatusClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusClimaticoRepository extends JpaRepository<StatusClimatico, Long> {

    // Encontra o status climático mais recente para uma dada localização
    Optional<StatusClimatico> findFirstByLocalizacaoOrderByDataHoraAtualizacaoDesc(Localizacao localizacao);
}

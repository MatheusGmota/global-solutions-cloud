package br.com.ecoalert.repositories;

import br.com.ecoalert.domain.entities.AlertaClimatico;
import br.com.ecoalert.domain.entities.Localizacao;
import br.com.ecoalert.domain.enums.StatusEvento;
import br.com.ecoalert.domain.enums.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlertaClimaticoRepository extends JpaRepository<AlertaClimatico, Long> {

    // Método para encontrar o alerta ativo mais recente para uma localização
    Optional<AlertaClimatico> findFirstByLocalizacaoAndStatusEventoOrderByDataHoraEmissaoDesc(Localizacao localizacao, StatusEvento statusEvento);

    // Método para encontrar todos os alertas para uma localização, ordenados por data
    List<AlertaClimatico> findByLocalizacaoOrderByDataHoraEmissaoDesc(Localizacao localizacao);

    List<AlertaClimatico> findFirstByLocalizacaoAndTipoEventoAndStatusEvento(Localizacao localizacao, TipoEvento tipoEvento, StatusEvento statusEvento);

    Optional<AlertaClimatico> findFirstByLocalizacaoAndTipoEventoAndStatusEventoOrderByDataHoraEmissaoDesc(Localizacao localizacao, TipoEvento tipoEvento, StatusEvento statusEvento);
}

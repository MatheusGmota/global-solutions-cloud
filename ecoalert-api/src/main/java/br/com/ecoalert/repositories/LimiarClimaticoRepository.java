package br.com.ecoalert.repositories;

import br.com.ecoalert.domain.entities.LimiarClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimiarClimaticoRepository extends JpaRepository<LimiarClimatico, Long> {
    List<LimiarClimatico> findByParametroSensor(String parametro);
}

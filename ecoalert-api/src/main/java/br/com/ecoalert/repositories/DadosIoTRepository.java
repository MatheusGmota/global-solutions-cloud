package br.com.ecoalert.repositories;

import br.com.ecoalert.domain.entities.DadosIoT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosIoTRepository extends JpaRepository<DadosIoT, Long> {}

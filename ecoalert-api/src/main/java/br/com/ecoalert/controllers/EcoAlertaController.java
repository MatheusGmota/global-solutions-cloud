package br.com.ecoalert.controllers;

import br.com.ecoalert.domain.entities.AlertaClimatico;
import br.com.ecoalert.domain.entities.StatusClimatico;
import br.com.ecoalert.dto.AlertaClimaticoResponse;
import br.com.ecoalert.dto.DadosIoTRequest;
import br.com.ecoalert.dto.StatusClimaticoResponse;
import br.com.ecoalert.services.EcoAlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EcoAlertaController {

    @Autowired
    EcoAlertaService service;

    @GetMapping("/status-climatico")
    public ResponseEntity<Object> obterStatusClimatico(@RequestParam String localizacao) {
        try {
            StatusClimaticoResponse statusClimatico = service.obterStatusClimatico(localizacao);
            if (statusClimatico != null) return ResponseEntity.ok(statusClimatico);
            else return ResponseEntity.status(404).body("Não foi possivel encontrar nenhum status para essa localização");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/alerta")
    public ResponseEntity<Object> obterAlertaClimatico(@RequestParam String localizacao) {
        try {
            AlertaClimaticoResponse alertaClimatico = service.obterAlertaClimatico(localizacao);
            if (alertaClimatico != null) return ResponseEntity.ok(alertaClimatico);
            else return ResponseEntity.status(404).body("Não foi possivel encontrar nenhum alerta.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/alerta/historico")
    public ResponseEntity<Object> obterHistoricoAlerta(@RequestParam String localizacao) {
        try {
            List<AlertaClimatico> alertaClimaticos = service.obterHistoricoAlerta(localizacao);
            if (alertaClimaticos != null) return ResponseEntity.ok(alertaClimaticos);
            else return ResponseEntity.status(404).body("Não foi possivel encontrar nenhum alerta.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar histórico de alertas: " + e.getMessage());
        }
    }

    @PostMapping("/dados-climaticos")
    public ResponseEntity<Object> receberDadosIoT(@Valid @RequestBody DadosIoTRequest dados) {
        try {
            service.processarDados(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body("Dados processados com sucesso.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao processar dados: " + e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar dados IoT: " + e.getMessage());
        }
    }

}

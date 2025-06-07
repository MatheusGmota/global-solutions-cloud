package br.com.ecoalert.controllers;

import br.com.ecoalert.domain.entities.LimiarClimatico;
import br.com.ecoalert.services.LimiarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/limiar")
@CrossOrigin(origins = "*")
public class LimiarController {

    @Autowired
    private LimiarService limiarService;

    @GetMapping
    public ResponseEntity<Object> listarTodos() {
        List<LimiarClimatico> lista = limiarService.ObterTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        LimiarClimatico limiar = limiarService.ObterPorId(id);
        if (limiar != null) {
            return ResponseEntity.ok(limiar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody LimiarClimatico limiar) {
        LimiarClimatico novoLimiar = limiarService.SalvarDados(limiar);
        return ResponseEntity.ok(novoLimiar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody LimiarClimatico limiarAtualizado) {
        LimiarClimatico existente = limiarService.ObterPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setParametroSensor(limiarAtualizado.getParametroSensor());
        existente.setValorMax(limiarAtualizado.getValorMax());
        existente.setValorMin(limiarAtualizado.getValorMin());
        existente.setMsgMax(limiarAtualizado.getMsgMax());
        existente.setMsgMin(limiarAtualizado.getMsgMin());
        existente.setRecomendacaoAlerta(limiarAtualizado.getRecomendacaoAlerta());

        LimiarClimatico atualizado = limiarService.EditarDados(existente);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deletar(@PathVariable Long id) {
        LimiarClimatico deletado = limiarService.DeletarDados(id);
        if (deletado != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

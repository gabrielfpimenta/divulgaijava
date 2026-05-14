package com.itb.inf2am.divulgai.controller;

import com.itb.inf2am.divulgai.model.entity.Prestador;
import com.itb.inf2am.divulgai.model.services.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @GetMapping
    public List<Prestador> findAll() {
        return prestadorService.findAll();
    }

    @PostMapping
    public Prestador create(@RequestBody Prestador prestador) {
        return prestadorService.save(prestador); // cadastro -> EM_ANALISE
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listarPrestadorPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(prestadorService.findById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "O id informado não é válido: " + id
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "Prestador não encontrado com o id " + id
                    )
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPrestador(@PathVariable String id, @RequestBody Prestador prestador) {
        try {
            Long prestadorId = Long.parseLong(id);
            Prestador prestadorAtualizado = prestadorService.update(prestadorId, prestador);
            return ResponseEntity.ok(prestadorAtualizado);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 400,
                            "error", "Bad Request",
                            "message", "O id informado não é válido: " + id
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "Prestador não encontrado com o id " + id
                    )
            );
        }
    }
}
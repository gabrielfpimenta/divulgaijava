package com.itb.inf2am.divulgai.controller;

import com.itb.inf2am.divulgai.model.entity.Contato;
import com.itb.inf2am.divulgai.model.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<Contato> findAll() {
        return contatoService.findAll();
    }

    @PostMapping
    public Contato create(@RequestBody Contato contato) {
        return contatoService.save(contato);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listarContatoPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(contatoService.findById(Long.parseLong(id)));
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
                            "message", e.getMessage()
                    )
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarContato(
            @PathVariable String id,
            @RequestBody Contato contato
    ) {
        try {
            Long contatoId = Long.parseLong(id);
            Contato contatoAtualizado = contatoService.update(contatoId, contato);
            return ResponseEntity.ok(contatoAtualizado);
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
                            "message", e.getMessage()
                    )
            );
        }
    }
}
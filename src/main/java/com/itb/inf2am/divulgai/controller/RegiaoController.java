package com.itb.inf2am.divulgai.controller;

import com.itb.inf2am.divulgai.model.entity.Regiao;
import com.itb.inf2am.divulgai.model.services.RegiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Getter (get): Apenas lê o valor do atributo.
// Setter (set): Apenas modifica o valor do atributo.

@RestController
@RequestMapping("/api/v1/regiao")

public class RegiaoController {

    @Autowired
    private RegiaoService regiaoService; // Service, não repository

    @GetMapping
    public List<Regiao> findAll() {
        return regiaoService.findAll(); // chama o service
    }

    @PostMapping
    public Regiao create(@RequestBody Regiao regiao) {
        return regiaoService.save(regiao); // chama o service
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> listarRegiaoPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(regiaoService.findById(Long.parseLong(id)));
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
                            "message", "Categoria não encontrada com o id " + id
                    )

            );

        }


    }

}

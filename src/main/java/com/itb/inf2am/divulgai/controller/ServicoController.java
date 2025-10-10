
    package com.itb.inf2am.divulgai.controller;

import com.itb.inf2am.divulgai.model.entity.Servico;
import com.itb.inf2am.divulgai.model.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Getter (get): Apenas lê o valor do atributo.
// Setter (set): Apenas modifica o valor do atributo.

    @RestController
    @RequestMapping("/api/v1/servico")

    public class ServicoController {

        @Autowired
        private ServicoService servicoService; // Service, não repository

        @GetMapping
        public List<Servico> findAll() {
            return servicoService.findAll(); // chama o service
        }

        @PostMapping
        public Servico create(@RequestBody Servico servico) {
            return servicoService.save(servico); // chama o service
        }


        @GetMapping("/{id}")
        public ResponseEntity<Object> listarServicoPorId(@PathVariable String id) {
            try {
                return ResponseEntity.ok(servicoService.findById(Long.parseLong(id)));
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
                                "message", "Servico não encontrada com o id " + id
                        )

                );

            }


        }
    }






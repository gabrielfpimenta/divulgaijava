 package com.itb.inf2am.divulgai.controller;

import com.itb.inf2am.divulgai.model.entity.Prestador;
import com.itb.inf2am.divulgai.model.services.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Getter (get): Apenas lê o valor do atributo.
// Setter (set): Apenas modifica o valor do atributo.

    @RestController
    @RequestMapping("/api/v1/prestador")

    public class PrestadorController {

        @Autowired
        private PrestadorService prestadorService; // Service, não repository

        @GetMapping
        public List<Prestador> findAll() {
            return prestadorService.findAll(); // chama o service
        }

        @PostMapping
        public Prestador create(@RequestBody Prestador prestador) {
            return prestadorService.save(prestador); // chama o service
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
                                "message", "Categoria não encontrada com o id " + id
                        )

                );

            }


        }

    }
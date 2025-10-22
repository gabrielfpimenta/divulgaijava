
package com.itb.inf2am.divulgai.controller;

import com.itb.inf2am.divulgai.model.entity.Feedback;
import com.itb.inf2am.divulgai.model.services.FeedbackService;
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
@RequestMapping("/api/v1/feedback")

public class FeedbackController {



    @Autowired
    private FeedbackService feedbackService; // Service, não repository

    @GetMapping
    public List<Feedback> findAll() {
        return feedbackService.findAll(); // chama o service
    }

    @PostMapping
    public Feedback create(@RequestBody Feedback feedback) {
        feedback.setDataCadastro(LocalDateTime.now());
        feedback.setStatusFeedback("ATIVO");
        return feedbackService.save(feedback); // salva e retorna
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> listarFeedbackPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(feedbackService.findById(Long.parseLong(id)));
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarFeedback(@PathVariable String id, @RequestBody Feedback feedback) {
        try {
            Long feedbackId = Long.parseLong(id);
            Feedback feedbackExistente = feedbackService.findById(feedbackId); // já lança exceção se não achar

            feedbackExistente.setDescricao(feedback.getDescricao());

            Feedback feedbackAtualizada = feedbackService.save(feedbackExistente);

            return ResponseEntity.ok(feedbackAtualizada);
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


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirFeedback(@PathVariable String id) {
        try {
            Long feedbackId = Long.parseLong(id);
            feedbackService.delete(feedbackId); // chama o service
            return ResponseEntity.ok(Map.of("message", "Categoria deletada com sucesso"));
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
                            "message", "Feedback não encontrado com o id " + id
                    )
            );
        }
    }
}






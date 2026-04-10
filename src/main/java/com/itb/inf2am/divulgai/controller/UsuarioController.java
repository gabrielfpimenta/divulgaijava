package com.itb.inf2am.divulgai.controller;

import com.itb.inf2am.divulgai.model.entity.Usuario;
import com.itb.inf2am.divulgai.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listarUsuarioPorId(@PathVariable String id) {
        try {
            Long usuarioId = Long.parseLong(id);
            Usuario usuario = usuarioService.findById(usuarioId);
            return ResponseEntity.ok(usuario);

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
                            "message", "Usuario não encontrado com o id " + id
                    )
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(
            @PathVariable String id,
            @RequestBody Usuario usuario
    ) {
        try {
            Long usuarioId = Long.parseLong(id);
            Usuario usuarioExistente = usuarioService.findById(usuarioId);

            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(usuario.getSenha());
            usuarioExistente.setNivelAcesso(usuario.getNivelAcesso());
            usuarioExistente.setPs_01(usuario.getPs_01());
            usuarioExistente.setPs_02(usuario.getPs_02());
            usuarioExistente.setFoto(usuario.getFoto());
            usuarioExistente.setStatusUsuario(usuario.getStatusUsuario());

            Usuario usuarioAtualizado = usuarioService.save(usuarioExistente);

            return ResponseEntity.ok(usuarioAtualizado);

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
                            "message", "Usuario não encontrado com o id " + id
                    )
            );
        }
    }

    @PutMapping("/{id}/foto")
    public ResponseEntity<Object> atualizarFotoUsuario(
            @PathVariable String id,
            @RequestBody Map<String, String> body
    ) {
        try {
            Long usuarioId = Long.parseLong(id);
            Usuario usuarioExistente = usuarioService.findById(usuarioId);

            String foto = body.get("foto");
            usuarioExistente.setFoto(foto);

            Usuario usuarioAtualizado = usuarioService.save(usuarioExistente);

            return ResponseEntity.ok(usuarioAtualizado);

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
                            "message", "Usuario não encontrado com o id " + id
                    )
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirUsuario(@PathVariable String id) {
        try {
            Long usuarioId = Long.parseLong(id);
            usuarioService.delete(usuarioId);

            return ResponseEntity.ok(
                    Map.of("message", "Usuario deletado com sucesso")
            );

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
                            "message", "Usuario não encontrado com o id " + id
                    )
            );
        }
    }
}
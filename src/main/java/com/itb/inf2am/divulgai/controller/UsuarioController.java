package com.itb.inf2am.divulgai.controller;


// No spring é comum adicionarmos anotações (annotation) em classes, atributos e métodos
// Uma annotation é uma forma de adicionar informações (metadados) ao seu código que podem
// ser interpretadas pelo compilador ou em tempo de execução por ferramentas e frameworks
// como o Spring
// Uma annotation é uma palavra iniciada com '@' que você coloca acima de uma class, método,
// atributo ou parâmetro, para dar instruções extras ao Java ou a algum framework sobre como
// aquele elemento deve ser tratado.

import com.itb.inf2am.divulgai.model.entity.Usuario;
import com.itb.inf2am.divulgai.model.entity.Usuario;
import com.itb.inf2am.divulgai.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/Usuario")
public class UsuarioController {

 @Autowired
 private UsuarioService usuarioService;

 @GetMapping
 public ResponseEntity<List<Usuario>> findAll() {

  return ResponseEntity.ok(usuarioService.findAll());
 }

 // @RequestBody : Corpo da Requisição ( Recebendo um objeto JSON )
 // RespondeEntity: Toda resposta HTTP (status, cabeçalhos e corpo), aqui temos mais controle sobre o que é devolvido para o cliente
 // 1. Status HTTP ( 200 ok, 201 CREATED, 404 NOT FOUND etc...)
 // 2. Headers: ( cabeçalhos extras, como Location, Authorization etc...)
 // 3. Body: ( o objeto que será convertido em JSON/XML para o cliente )

 @PostMapping
 public ResponseEntity<Usuario> save(@RequestBody Usuario Usuario) {

  Usuario newUsuario = UsuarioService.save(Usuario);
  return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
 }

 @PostMapping
 public Usuario create(@RequestBody Usuario usuario) {
  return usuarioService.save(usuario); // chama o service
 }


 @GetMapping("/{id}")
 public ResponseEntity<Object> listarUsuarioPorId(@PathVariable String id) {
  try {
   return ResponseEntity.ok(usuarioService.findById(Long.parseLong(id)));
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
                   "message", "Usuario não encontrada com o id " + id
           )

   );

  }


 }

 @PutMapping("/{id}")
 public ResponseEntity<Object> atualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
  try {
   Long usuarioId = Long.parseLong(id);
   Usuario usuarioExistente = usuarioService.findById(usuarioId); // já lança exceção se não achar

   usuarioExistente.setNome(usuario.getNome());

   Usuario usuarioAtualizada = usuarioService.save(usuarioExistente);

   return ResponseEntity.ok(usuarioAtualizada);
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
                   "message", "Usuario não encontrada com o id " + id
           )
   );
  }
 }


 @DeleteMapping("/{id}")
 public ResponseEntity<Object> excluirUsuario(@PathVariable String id) {
  try {
   Long usuarioId = Long.parseLong(id);
   usuarioService.delete(usuarioId); // chama o service
   return ResponseEntity.ok(Map.of("message", "Usuario deletada com sucesso"));
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
                   "message", "Usuario não encontrada com o id " + id
           )
   );
  }
 }
}


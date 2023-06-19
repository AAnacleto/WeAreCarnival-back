package wearecarnival.com.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wearecarnival.com.models.Usuario;
import wearecarnival.com.services.UsuarioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/wearecarnival/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Usuario usuario) {
        Map<HttpStatus, String> message = new HashMap<>();
        Usuario base;

        base = usuarioService.findByEmail(usuario.getEmail());
        if(base != null) {
            if(base.getEmail().equals(usuario.getEmail())) {
                message.put(HttpStatus.CONFLICT, "Usuário já cadastrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            }
        }

        if(usuario.getEmail().isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Por favor, insira email do usuário");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        usuarioService.save(usuario);
        message.put(HttpStatus.CREATED, "Usuário criado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id")UUID idUsuario) {
        Map<HttpStatus, String> message = new HashMap<>();
        Usuario base;

        base = usuarioService.findById(idUsuario);
        if(base == null) {
            message.put(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        usuarioService.delete(idUsuario);
        message.put(HttpStatus.ACCEPTED, "Usuário deletado com sucesso!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID idUsuario) {
        Map<HttpStatus, String> message = new HashMap<>();
        Usuario base = usuarioService.findById(idUsuario);
        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Disciplina não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }
    @GetMapping("/find/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value = "email") String email) {
        Map<HttpStatus, String> message = new HashMap<>();
        Usuario base = usuarioService.findByEmail(email);
        if(base.getEmail().equals(email)) {
            message.put(HttpStatus.CONFLICT, "Email existente");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }
    @GetMapping("/find/name/{nome}")
    public ResponseEntity<Object> findById(@PathVariable(value = "nome") String nome) {
        Map<HttpStatus, String> message = new HashMap<>();
        if (nome.isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Deve informar o nome do usuário para realizar a pesquisa!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        Usuario base = null;
        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Usuario não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Usuario>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }
}

package wearecarnival.com.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wearecarnival.com.models.Usuario;
import wearecarnival.com.services.UsuarioService;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/wac/usuario")
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
}

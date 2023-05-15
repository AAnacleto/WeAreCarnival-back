package wearecarnival.com.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wearecarnival.com.models.Endereco;
import wearecarnival.com.services.EnderecoService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/wearecarnival/endereco")
public class EnderecoController{

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Endereco endereco){
        Map<HttpStatus, String> message = new HashMap<>();

        if(endereco.getNomeRua().isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Favor preencher endereco!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        enderecoService.save(endereco);
        message.put(HttpStatus.CREATED, "Endereco cadastrado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);

    }

    @GetMapping("/find/byId/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Map<HttpStatus, String> message = new HashMap<>();
        Endereco base;

        base = enderecoService.findById(id);
        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Endereco não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }
}

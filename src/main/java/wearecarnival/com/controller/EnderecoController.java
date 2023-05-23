package wearecarnival.com.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wearecarnival.com.models.Endereco;
import wearecarnival.com.services.EnderecoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/wearecarnival/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Endereco endereco) {
        Map<HttpStatus, String> message = new HashMap<>();

        if (endereco.getNomeRua().isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Favor preencher endereco!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        enderecoService.save(endereco);
        message.put(HttpStatus.CREATED, "Endereco cadastrado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);

    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Endereco endereco) {
        Map<HttpStatus, String> message = new HashMap<>();
        Endereco base;
        base = enderecoService.findById(endereco.getId());

        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Evento não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        if (endereco.getNomeRua().isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Favor preencher o nome do Evento!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        enderecoService.update(endereco);
        message.put(HttpStatus.ACCEPTED, "Evento alterado com sucesso!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID idEndereco) {
        Map<HttpStatus, String> message = new HashMap<>();
        Endereco base;

        base = enderecoService.findById(idEndereco);
        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Endereço não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        enderecoService.delete(idEndereco);
        message.put(HttpStatus.ACCEPTED, "Endereço removido com sucesso!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
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

    @GetMapping("/find/name/{nomeRua}")
    public ResponseEntity<Object> findById(@PathVariable(value = "nomeRua") String nome) {
        Map<HttpStatus, String> message = new HashMap<>();
        if(nome.isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Por favor, informar nome da rua existente");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        Endereco base = null;
        if(base == null) {
            message.put(HttpStatus.NOT_FOUND, "Endereço não encontrado!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Endereco>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
    }
}

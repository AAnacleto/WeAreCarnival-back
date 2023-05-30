package wearecarnival.com.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wearecarnival.com.models.Eventos;
import wearecarnival.com.services.EventosService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/wearecarnival/eventos")
public class EventosController {

    @Autowired
    private EventosService service;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Eventos eventos) {
        Map<HttpStatus, String> message = new HashMap<>();
        Eventos base = service.findByName(eventos.getNome());

        if(base != null){
            if(eventos.getData().getYear() == base.getData().getYear()) {
                message.put(HttpStatus.CONFLICT, "Evento já cadastrado!");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            }
        }

        if (eventos.getNome().isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Favor preencher o nome do evento!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        service.save(eventos);
        message.put(HttpStatus.CREATED, "Evento cadastrado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);

    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Eventos eventos) {
        Map<HttpStatus, String> message = new HashMap<>();
        Eventos base;
        base = service.findById(eventos.getId());

        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Evento não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        if (eventos.getNome().isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Favor preencher o nome do Evento!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        service.update(eventos);
        message.put(HttpStatus.ACCEPTED, "Evento alterado com sucesso!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

    @DeleteMapping("/delete/{idEvento}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "idEvento") UUID idEvento) {
        Map<HttpStatus, String> message = new HashMap<>();
        Eventos base;
        base = service.findById(idEvento);

        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Evento não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        service.delete(idEvento);
        message.put(HttpStatus.ACCEPTED, "Evento deletado com sucesso!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

    @GetMapping("/find/byId/{idEvento}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idEvento") UUID idEvento) {
        Map<HttpStatus, String> message = new HashMap<>();
        Eventos base;

        base = service.findById(idEvento);
        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Evento não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }

    @GetMapping(value = "/find/byDay/{dayOfWeek}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Eventos>> findByDay(@PathVariable(value = "dayOfWeek") int dayOfWeek) {
        List<Eventos> base;
        base = service.findByDay(dayOfWeek);
        return ResponseEntity.status(HttpStatus.OK).body(base);

    }

    @GetMapping(value = "/find/byFavorite/{valor}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Eventos>> findByFavorite(@PathVariable(value = "valor") boolean valor) {
        List<Eventos> base;
        base = service.findByFavorite(valor);
        return ResponseEntity.status(HttpStatus.OK).body(base);

    }

    @GetMapping(value = "/find/byCity/{nomeCidade}")
    public ResponseEntity<Object> findByCity(@PathVariable(value = "nomeCidade") String nomeCidade) {
        List<Eventos> base;
        base = service.findByCity(nomeCidade);

        return ResponseEntity.status(HttpStatus.OK).body(base);
    }


    @GetMapping("/find/all")
    public ResponseEntity<List<Eventos>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }


}

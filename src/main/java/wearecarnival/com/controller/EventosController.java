package wearecarnival.com.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wearecarnival.com.models.Eventos;
import wearecarnival.com.services.EventosService;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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

//        try {
//            if(UploadImage.fazerUploadImagem(imagem)) {
//                eventos.setImagem(imagem.getOriginalFilename());
//            }
//        } catch (Exception e) {
//            e.getMessage();
//        }

        service.save(eventos);
        message.put(HttpStatus.CREATED, "Evento cadastrado com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);

    }

    @GetMapping("/imagem")
    public byte[] getImagem(@RequestParam("imagem") String imagem) {
        File imagemArquio = new File("src/main/resources/static/images/img-evento/" + imagem);
        try {
            return Files.readAllBytes(imagemArquio.toPath());
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }


    @PutMapping("/update/{idEvento}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id , @RequestBody Eventos eventos) {
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

        service.update(id, eventos);
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
    public ResponseEntity<List<Eventos>> findByDay(@PathVariable(value = "dayOfWeek") String dayOfWeek) {
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
    @GetMapping(value = "/find/{nome}")
    public ResponseEntity<Object> findByName(@PathVariable(value = "nome") String nome) {
        Eventos  base;
        base = service.findByName(nome);
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }

    @GetMapping(value = "/find/byEventoNome/{nome}")
    public ResponseEntity<Object> findByEventName(@PathVariable(value = "nome") String nome) {
        List<Eventos> base;
        base = service.findByEventName(nome);
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }
    @GetMapping(value = "/find/byCity/{nomeCidade}")
    public ResponseEntity<Object> findByCity(@PathVariable(value = "nomeCidade") String nomeCidade) {
        List<Eventos> base;
        base = service.findByCity(nomeCidade);

        return ResponseEntity.status(HttpStatus.OK).body(base);
    }

    @GetMapping(value = "/find/byCategory/{categoria}")
    public ResponseEntity<Object> findByCategory(@PathVariable(value = "categoria") String categoria) {
        List<Eventos> base;
        base = service.findByCategory(categoria);

        return ResponseEntity.status(HttpStatus.OK).body(base);
    }

    @GetMapping("/find/cityDay/?dia={dia}&cidade={cidade}")
    public ResponseEntity<List<Eventos>> buscarEventosPorDiaECidade(
            @RequestParam("dia") Integer dia,
            @RequestParam("cidade") String cidade
    ) {
        List<Eventos> eventos = service.procurarEventosPorDiaECidade(dia, cidade);
        if (eventos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventos);
    }


    @GetMapping("/find/all")
    public ResponseEntity<List<Eventos>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/find/intDay/{diaInt}")
    public ResponseEntity<List<Eventos>> buscarDiaInt(@PathVariable(value = "diaInt") Integer diaInt) {
       List<Eventos> base = service.buscarDiaInt(diaInt);

       return ResponseEntity.status(HttpStatus.OK).body(base);
    }


}

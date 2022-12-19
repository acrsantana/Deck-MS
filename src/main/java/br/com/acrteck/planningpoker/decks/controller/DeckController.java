package br.com.acrteck.planningpoker.decks.controller;

import br.com.acrteck.planningpoker.decks.dto.DeckDto;
import br.com.acrteck.planningpoker.decks.exception.DeckNaoEncontradoException;
import br.com.acrteck.planningpoker.decks.exception.FalhaAoRecuperarArquivoException;
import br.com.acrteck.planningpoker.decks.exception.FalhaAoRecuperarCartasException;
import br.com.acrteck.planningpoker.decks.exception.FalhaAoRecuperarDecksException;
import br.com.acrteck.planningpoker.decks.service.DeckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import java.util.List;

@Controller @Slf4j
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping("/id/{id}/organizacao/{idOrganizacao}")
    public ResponseEntity<DeckDto> findById(@PathVariable Long id, @PathVariable Integer idOrganizacao){
        try {
            return ResponseEntity.ok(deckService.findById(id, idOrganizacao));
        } catch (FalhaAoRecuperarArquivoException | FalhaAoRecuperarCartasException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DeckNaoEncontradoException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nome/{nome}/organizacao/{organizacao}")
    public ResponseEntity<DeckDto> findByNome(@PathVariable String nome, @PathVariable Integer organizacao){
        try {
            return ResponseEntity.ok(deckService.findByName(nome, organizacao));
        } catch (FalhaAoRecuperarArquivoException | FalhaAoRecuperarCartasException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DeckNaoEncontradoException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DeckDto>> findAll() {
        try {
            return ResponseEntity.ok(deckService.findAll());
        } catch (FalhaAoRecuperarDecksException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{nome}")
    @Consumes({"multipart/form-data,application/json"})
    public  ResponseEntity<DeckDto> save(@PathVariable String nome,
        @RequestPart("arquivo") @Valid @NotNull MultipartFile arquivo
    ){
        try {
            DeckDto deckDto = new DeckDto();
            deckDto.setNome(nome);
            deckDto.setArquivo(arquivo.getBytes());
            return ResponseEntity.ok(deckService.save(deckDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

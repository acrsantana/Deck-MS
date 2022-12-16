package br.com.acrteck.planningpoker.decks.service;

import br.com.acrteck.planningpoker.decks.dto.CartaDto;
import br.com.acrteck.planningpoker.decks.dto.DeckDto;
import br.com.acrteck.planningpoker.decks.exception.DeckNaoEncontradoException;
import br.com.acrteck.planningpoker.decks.exception.FalhaAoRecuperarCartasException;
import br.com.acrteck.planningpoker.decks.exception.FalhaAoRecuperarDecksException;
import br.com.acrteck.planningpoker.decks.http.CartasClient;
import br.com.acrteck.planningpoker.decks.model.Deck;
import br.com.acrteck.planningpoker.decks.model.Organizacao;
import br.com.acrteck.planningpoker.decks.repository.DeckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j
public class DeckService {

    private final DeckRepository deckRepository;
    private final CartasClient cartasClient;

    public DeckService(DeckRepository deckRepository, CartasClient cartasClient) {
        this.deckRepository = deckRepository;
        this.cartasClient = cartasClient;
    }

    public DeckDto save(DeckDto deckDto) {
        return new DeckDto(deckRepository.save(new Deck(deckDto)));
    }

    public DeckDto findById(Long id, Organizacao organizacao){
        DeckDto deck = recuperaDeckPeloId(id);
        deck.setOrganizacao(organizacao);
        recuperaCartas(deck);
        return deck;
    }

    public DeckDto findByName(String nome, Organizacao organizacao){
        DeckDto deck = recuperaDeckPeloNome(nome);
        deck.setOrganizacao(organizacao);
        recuperaCartas(deck);
        return deck;
    }

    public List<DeckDto> findAll() {
        log.info("Buscando todos os decks (sem cartas)");
        try {
            return deckRepository.findAll().stream().map(DeckDto::new).toList();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FalhaAoRecuperarDecksException(e.getMessage(), e);
        }

    }

    private void recuperaCartas(DeckDto deck) {
        log.info("Buscando cartas vinculadas ao deck {}, organização {}", deck.getNome(), deck.getOrganizacao().name());
        try {
            List<CartaDto> cartas = cartasClient.obtemCartasPorOrganizacao(deck.getOrganizacao());
            log.debug("Buscou as cartas no microsserviço");
            deck.setCartas(cartas);
        } catch (Exception e) {
            log.error("Falha ao recuperar cartas, tente novamente mais tarde.");
            throw new FalhaAoRecuperarCartasException("Falha ao recuperar cartas, tente novamente mais tarde.", e);
        }
    }

    private DeckDto recuperaDeckPeloId(Long id) {
        log.info("Recuperando o deck com id {}", id);
        Optional<Deck> optionalDeck = deckRepository.findById(id);
        log.debug("Buscou o deck no banco de dados");
        if (optionalDeck.isEmpty()) {
            log.error("Deck com id {} não existe", id);
            throw new DeckNaoEncontradoException("O deck informado não existe");
        }
        return new DeckDto(optionalDeck.get());
    }

    private DeckDto recuperaDeckPeloNome(String nome) {
        log.info("Recuperando o deck com nome {}", nome);
        Optional<Deck> optionalDeck = deckRepository.findByNome(nome);
        log.debug("Buscou o deck no banco de dados");
        if (optionalDeck.isEmpty()) {
            log.error("Deck com o nome {} não existe", nome);
            throw new DeckNaoEncontradoException("O deck informado não existe");
        }
        return new DeckDto(optionalDeck.get());
    }
}

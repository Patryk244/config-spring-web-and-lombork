package com.crud.tasks.trello.client;

import com.crud.tasks.domain.Badges;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.username}")
    private String trelloUsername;
    @Value("${trello.app.card}")
    private String trelloCardId;

    private URI buildTrelloUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .queryParam("token", trelloToken)
                .build().encode().toUri();
    }


    private URI postNewCardToTrello(TrelloCardDto trelloCardDto) {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();
    }

    private URI getCardForTrello() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards/" +  trelloCardId)
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("badges", "all")
                .build().encode().toUri();
    }

    public List<CreatedTrelloCard> getCreatedTrelloCards() {
        CreatedTrelloCard[] cardsResponse = restTemplate.getForObject(getCardForTrello(), CreatedTrelloCard[].class);
        return Arrays.asList(cardsResponse);
    }

    public List<TrelloBoardDto> getTrelloBoards() {
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildTrelloUrl(), TrelloBoardDto[].class);
        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        return restTemplate.postForObject(postNewCardToTrello(trelloCardDto), null, CreatedTrelloCard.class);
    }


}

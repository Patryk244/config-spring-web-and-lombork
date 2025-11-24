package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TrelloClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);
    private final RestTemplate restTemplate;
    private final TrelloConfig trelloConfig;



    private URI buildTrelloUrl() {

        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .queryParam("token", trelloConfig.getTrelloToken())
                .build().encode().toUri();
    }


    private URI postNewCardToTrello(TrelloCardDto trelloCardDto) {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();
    }

    private URI getCardForTrello() {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" +  trelloConfig.getTrelloCardId())
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("badges", "all")
                .build().encode().toUri();
    }

    public List<CreatedTrelloCardDto> getCreatedTrelloCards() {
        CreatedTrelloCardDto[] cardsResponse = restTemplate.getForObject(getCardForTrello(), CreatedTrelloCardDto[].class);
        return Arrays.asList(cardsResponse);
    }

    public List<TrelloBoardDto> getTrelloBoards() {
        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildTrelloUrl(), TrelloBoardDto[].class);
            return Optional.ofNullable(boardsResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {
        return restTemplate.postForObject(postNewCardToTrello(trelloCardDto), null, CreatedTrelloCardDto.class);
    }


}

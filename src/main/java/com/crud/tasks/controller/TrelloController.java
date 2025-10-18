package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {
    private final TrelloClient trelloService;
    private final RestTemplate restTemplate;

    @GetMapping("boards")
    public void getTrelloBoards(){
        List<TrelloBoardDto> trelloBoards = trelloService.getTrelloBoards();
        /*
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName().equals("Kodiila"));
        });

         */
        /*
        trelloBoards.stream()
                .filter(b -> b.getName().contains("Kodilla"))
                .map(b -> b.getId() + " " + b.getName())
                .forEach(System.out::println);

         */
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
            System.out.println("This board contains lists: ");
            trelloBoardDto.getLists().forEach(trelloList -> {
                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
            });
        });
    }

    @GetMapping("createdCard")
    public void getCreatedCard(){
        List<CreatedTrelloCard> trelloCards = trelloService.getCreatedTrelloCards();
        trelloCards.forEach(trelloCard -> {
           System.out.println(trelloCard.getBadges().getAttachmentsByType().getTrello().getCard());
        });


    }

    @PostMapping("cards")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createNewCard(trelloCardDto);
    }
}
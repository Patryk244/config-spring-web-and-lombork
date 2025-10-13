package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {
    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public void getTrelloBoards(){
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        /*
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName().equals("Kodiila"));
        });

         */
        trelloBoards.stream()
                .filter(b -> b.getName().contains("Kodilla"))
                .map(b -> b.getId() + " " + b.getName())
                .forEach(System.out::println);
    }
}
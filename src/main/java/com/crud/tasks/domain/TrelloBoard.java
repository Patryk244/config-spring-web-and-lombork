package com.crud.tasks.domain;

import lombok.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloBoard {
    private String id;
    private String name;
    private List<TrelloList> lists;
}
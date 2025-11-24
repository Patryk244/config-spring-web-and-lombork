package com.crud.tasks.domain;

import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloList {
    private String id;
    private String name;
    private boolean isClosed;
}
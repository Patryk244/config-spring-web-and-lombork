package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrelloBoardDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lists")
    private List<TrelloListDto> lists;
}

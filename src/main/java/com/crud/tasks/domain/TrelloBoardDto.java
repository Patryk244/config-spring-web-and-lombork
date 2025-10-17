package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrelloBoardDto {
    @JsonProperty("id")
    private String name;
    @JsonProperty("name")
    private String id;
    @JsonProperty("lists")
    private List<TrelloListDto> lists;
}

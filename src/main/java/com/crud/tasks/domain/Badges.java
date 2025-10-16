package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Badges {
    @JsonProperty("votes")
    private int votes;
    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachmentsByType;
}

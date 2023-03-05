package br.com.apibiblia.integration.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {
    AbbrevResponse abbrev;
    private String author;
    private float chapters;
    private String group;
    private String name;
    private String testament;
    private String comment;

}

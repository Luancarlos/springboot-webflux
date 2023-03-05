package br.com.apibiblia.integration.client;

import br.com.apibiblia.integration.response.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BookClient {

    @Value("${api.biblia}")
    private String apiBiblia;

    private final WebClient webClient;

    public BookClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Flux<BookResponse> getBooks() {
        log.info("Get books from api");
        return this.webClient.get()
                .uri(apiBiblia + "books")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(BookResponse.class);
    }

    public Mono<BookResponse> getBookByAbbrev(String abbrev) {
        log.info("get book by abrev: {}", abbrev);
        return this.webClient.get()
                .uri(apiBiblia + "books/" + abbrev)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        httpStatusCode -> httpStatusCode.is4xxClientError(),
                        error -> Mono.error(new RuntimeException("Verifique a abreviação enviada")))
                .bodyToMono(BookResponse.class);
    }

}

package br.com.apibiblia.controller;

import br.com.apibiblia.integration.client.BookClient;
import br.com.apibiblia.integration.response.BookResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookClient bookClient;

    public BookController(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @GetMapping("")
    public Flux<BookResponse> getBooks() {
        return bookClient.getBooks();
    }

    @GetMapping("/{abbrev}")
    public Mono<BookResponse> getBookByAbbreve(@PathVariable String abbrev) {
        return bookClient.getBookByAbbrev(abbrev);
    }

}

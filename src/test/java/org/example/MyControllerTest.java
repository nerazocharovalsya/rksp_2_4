package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MyControllerTest {

    @InjectMocks
    private MyController controller;

    @Mock
    private MyRepository repository;

    @Test
    void testFindById() {
        long id = 123L;
        MyData expectedData = new MyData(id, "Test Data", 1, 1f);
        given(repository.findMyDataById(id)).willReturn(expectedData);

        Mono<MyData> result = controller.findById(id);

        StepVerifier.create(result)
                .expectNextMatches(data -> data.getId().equals(id))
                .verifyComplete();
    }

    @Test
    void testDelete() {
        long id = 456L;
        MyData existingData = new MyData(id, "Test Data", 1, 1f);
        given(repository.findMyDataById(id)).willReturn(existingData);

        Mono<Void> result = controller.deleteCat(id);

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(repository).delete(existingData);
    }
}
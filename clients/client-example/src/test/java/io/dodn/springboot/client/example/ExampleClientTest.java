package io.dodn.springboot.client.example;

import static org.assertj.core.api.Assertions.assertThat;

import feign.RetryableException;

import io.dodn.springboot.client.ClientExampleContextTest;

import org.junit.jupiter.api.Test;

public class ExampleClientTest extends ClientExampleContextTest {

    private final ExampleClient exampleClient;

    public ExampleClientTest(ExampleClient exampleClient) {
        this.exampleClient = exampleClient;
    }

    @Test
    public void shouldBeThrownExceptionWhenExample() {
        try {
            exampleClient.example("HELLO!");
        }
        catch (Exception e) {
            assertThat(e).isExactlyInstanceOf(RetryableException.class);
        }
    }

}

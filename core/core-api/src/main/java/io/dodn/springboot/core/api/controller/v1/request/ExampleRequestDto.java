package io.dodn.springboot.core.api.controller.v1.request;

import io.dodn.springboot.core.api.domain.ExampleData;

public record ExampleRequestDto(String data) {
    public ExampleData toExampleData() {
        return new ExampleData(data, data);
    }
}

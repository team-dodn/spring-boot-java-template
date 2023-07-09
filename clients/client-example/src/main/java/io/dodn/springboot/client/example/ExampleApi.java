package io.dodn.springboot.client.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "example-api", url = "${example.api.url}")
interface ExampleApi {

    @RequestMapping(method = RequestMethod.POST, value = "/example/example-api",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ExampleResponseDto example(@RequestBody ExampleRequestDto request);

}

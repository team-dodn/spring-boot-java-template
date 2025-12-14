package io.dodn.springboot.test.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsTest {

    protected MockMvc mockMvc;

    private RestDocumentationContextProvider restDocumentation;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.restDocumentation = restDocumentation;
    }

    protected MockMvc mockController(Object controller) {
        return MockMvcBuilders.standaloneSetup(controller)
            .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
            .build();
    }

}

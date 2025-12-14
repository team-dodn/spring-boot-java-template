package io.dodn.springboot.core.api.controller.v1;

import io.dodn.springboot.core.api.controller.v1.request.ExampleRequestDto;
import io.dodn.springboot.core.domain.ExampleResult;
import io.dodn.springboot.core.domain.ExampleService;
import io.dodn.springboot.test.api.RestDocsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import tools.jackson.databind.json.JsonMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExampleControllerTest extends RestDocsTest {

    private ExampleService exampleService;

    @BeforeEach
    public void setUp() {
        exampleService = mock(ExampleService.class);
        mockMvc = mockController(new ExampleController(exampleService));
    }

    @Test
    public void exampleGet() throws Exception {
        when(exampleService.processExample(any())).thenReturn(new ExampleResult("BYE"));

        mockMvc
            .perform(get("/get/{exampleValue}", "HELLO_PATH").param("exampleParam", "HELLO_PARAM")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(document("exampleGet", Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    pathParameters(parameterWithName("exampleValue").description("ExampleValue")),
                    queryParameters(parameterWithName("exampleParam").description("ExampleParam")),
                    responseFields(fieldWithPath("result").type(JsonFieldType.STRING).description("ResultType"),
                            fieldWithPath("data.result").type(JsonFieldType.STRING).description("Result Data"),
                            fieldWithPath("data.date").type(JsonFieldType.STRING).description("Result Date"),
                            fieldWithPath("data.datetime").type(JsonFieldType.STRING).description("Result Datetime"),
                            fieldWithPath("data.items").type(JsonFieldType.ARRAY).description("Result Items"),
                            fieldWithPath("data.items[].key").type(JsonFieldType.STRING).description("Result Item"),
                            fieldWithPath("error").type(JsonFieldType.NULL).ignored())));
    }

    @Test
    public void examplePost() throws Exception {
        when(exampleService.processExample(any())).thenReturn(new ExampleResult("BYE"));

        mockMvc
            .perform(post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapper.builder().build().writeValueAsString(new ExampleRequestDto("HELLO_BODY"))))
            .andExpect(status().isOk())
            .andDo(document("examplePost", Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    requestFields(
                            fieldWithPath("data").type(JsonFieldType.STRING).description("ExampleBody Data Field")),
                    responseFields(fieldWithPath("result").type(JsonFieldType.STRING).description("ResultType"),
                            fieldWithPath("data.result").type(JsonFieldType.STRING).description("Result Data"),
                            fieldWithPath("data.date").type(JsonFieldType.STRING).description("Result Date"),
                            fieldWithPath("data.datetime").type(JsonFieldType.STRING).description("Result Datetime"),
                            fieldWithPath("data.items").type(JsonFieldType.ARRAY).description("Result Items"),
                            fieldWithPath("data.items[].key").type(JsonFieldType.STRING).description("Result Item"),
                            fieldWithPath("error").type(JsonFieldType.STRING).ignored())));
    }

}

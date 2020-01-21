package com.myCompanyName.myProjectName;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@AutoConfigureMockMvc
@ActiveProfiles("web_integration_test")
public abstract class WebMvcTest extends IntegrationTests {

  @Autowired
  protected MockMvc mockMvc;

  protected ResultActions performGET(final String url)
      throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get(url);

    requestBuilder.contentType(APPLICATION_JSON);
    return this.mockMvc.perform(requestBuilder);
  }

  protected ResultActions performPOST(final String url, final String jsonContent) throws Exception {
    MockHttpServletRequestBuilder requestBuilder = post(url);

    if (jsonContent != null) {
      requestBuilder.contentType(APPLICATION_JSON);
      requestBuilder.content(jsonContent);
    }
    return this.mockMvc.perform(requestBuilder);
  }

}

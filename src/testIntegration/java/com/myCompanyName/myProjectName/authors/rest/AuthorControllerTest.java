package com.myCompanyName.myProjectName.authors.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.myCompanyName.myProjectName.ApiMatchers;
import com.myCompanyName.myProjectName.WebMvcTest;
import com.myCompanyName.myProjectName.generated.model.Author;
import com.myCompanyName.myProjectName.generated.model.AuthorList;
import com.myCompanyName.myProjectName.generated.model.Error;
import org.junit.jupiter.api.Test;

public class AuthorControllerTest extends WebMvcTest {

  private static final String URL = "/authors/";

  @Test
  public void WHEN_get_authors_THEN_response_contains_correct_data_structure() throws Exception {
    // Act
    performGET(URL)

        // Assert
        .andExpect(status().isOk())
        .andExpect(ApiMatchers.responseMatchesModel(AuthorList.class));
  }

  @Test
  public void GIVEN_id_WHEN_get_author_by_id_THEN_response_contains_correct_data_structure()
      throws Exception {
    // Arrange
    String id = "1";

    // Act
    performGET(URL + id)

        // Assert
        .andExpect(status().isOk())
        .andExpect(ApiMatchers.responseMatchesModel(Author.class));
  }

  @Test
  public void GIVEN_not_existing_id_WHEN_get_author_by_id_THEN_response_is_error()
      throws Exception {
    // Arrange
    String id = "2";

    // Act
    performGET(URL + id)

        // Assert
        .andExpect(status().isInternalServerError())
        .andExpect(ApiMatchers.responseMatchesModel(Error.class));
  }

}

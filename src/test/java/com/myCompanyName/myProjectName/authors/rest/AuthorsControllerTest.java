package com.myCompanyName.myProjectName.authors.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.myCompanyName.myProjectName.generated.model.AuthorList;
import com.myCompanyName.myProjectName.generated.model.Author;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class AuthorsControllerTest {

  @Test
  public void WHEN_get_authors_THEN_response_is_not_null() {
    // Arrange
    AuthorController authorController = new AuthorController();

    // Act
    ResponseEntity<AuthorList> authors = authorController.getAuthors(null, null);

    // Assert
    assertThat(authors.getBody(), is(notNullValue()));
  }

  @Test
  public void WHEN_get_authors_THEN_response_contains_list_of_authors() {
    // Arrange
    AuthorController authorController = new AuthorController();

    // Act
    ResponseEntity<AuthorList> authors = authorController.getAuthors(null, null);

    // Assert
    List<Author> authorList = authors.getBody().getEmbedded();
    assertThat(authorList, is(notNullValue()));
    assertThat(authorList.size(), is(2));
  }

  @Test
  public void WHEN_get_author_by_id_THEN_response_is_not_null() {
    // Arrange
    AuthorController authorController = new AuthorController();

    // Act
    ResponseEntity<Author> author = authorController.getAuthorById(1);

    // Assert
    assertThat(author, is(notNullValue()));
  }

  @Test
  public void WHEN_get_author_by_id_THEN_response_contains_author_with_data() {
    // Arrange
    AuthorController authorController = new AuthorController();

    // Act
    ResponseEntity<Author> response = authorController.getAuthorById(1);

    // Assert
    Author author = response.getBody();
    assertThat(author, is(notNullValue()));
    assertThat(author.getFirstname(), is(equalTo("Max")));
    assertThat(author.getLastname(), is(equalTo("Mustermann")));
  }

}

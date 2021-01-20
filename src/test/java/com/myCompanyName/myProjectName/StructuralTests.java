package com.myCompanyName.myProjectName;

import org.junit.jupiter.api.Test;
import org.moduliths.model.Modules;

public class StructuralTests {

  @Test
  void WHEN_code_is_analyzed_THEN_code_has_modulithic_structure() {
    Modules.of(Application.class).verify();
  }
}

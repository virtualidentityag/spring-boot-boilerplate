package com.myCompanyName.myProjectName;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import org.junit.jupiter.api.Test;
import org.moduliths.model.Modules;

public class StructuralTests {

  @Test
  void WHEN_code_is_analyzed_THEN_code_has_modulithic_structure() {
    Modules.of(Application.class, thatAreNotGenerated()).verify();
  }

  private DescribedPredicate<JavaClass> thatAreNotGenerated() {
    return new DescribedPredicate<JavaClass>("generated classes") {
      @Override
      public boolean apply(JavaClass input) {
        return input.getPackageName().startsWith("com.myCompanyName.myProjectName.generated");
      }
    };
  }
}

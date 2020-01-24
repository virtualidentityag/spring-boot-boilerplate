package com.myCompanyName.myProjectName.common.rest.error;

public enum RestErrorCode {

  SERVER_ERROR("2000"),
  VALIDATION_EXCEPTION("2001")
  // TODO add your own error codes
  ;

  private String errorCode;

  RestErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}

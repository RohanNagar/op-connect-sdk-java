package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Type {
  STRING,
  EMAIL,
  CONCEALED,
  URL,
  OTP,
  DATE,
  MONTH_YEAR,
  MENU,
  CREDIT_CARD_TYPE,
  CREDIT_CARD_NUMBER,
  PHONE,
  ADDRESS,
  GENDER,

  @JsonEnumDefaultValue
  UNKNOWN
}

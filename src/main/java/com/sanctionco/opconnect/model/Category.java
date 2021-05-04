package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

/**
 * Represents an item category.
 */
public enum Category {
  /**
   * The login category.
   */
  LOGIN,

  /**
   * The password category.
   */
  PASSWORD,

  /**
   * The server category.
   */
  SERVER,

  /**
   * The database category.
   */
  DATABASE,

  /**
   * The credit card category.
   */
  CREDIT_CARD,

  /**
   * The membership category.
   */
  MEMBERSHIP,

  /**
   * The passport category.
   */
  PASSPORT,

  /**
   * The software license category.
   */
  SOFTWARE_LICENSE,

  /**
   * The outdoor license category.
   */
  OUTDOOR_LICENSE,

  /**
   * The secure note category.
   */
  SECURE_NOTE,

  /**
   * The wireless router category.
   */
  WIRELESS_ROUTER,

  /**
   * The bank account category.
   */
  BANK_ACCOUNT,

  /**
   * The driver license category.
   */
  DRIVER_LICENSE,

  /**
   * The identity category.
   */
  IDENTITY,

  /**
   * The reward program category.
   */
  REWARD_PROGRAM,

  /**
   * The document category.
   */
  DOCUMENT,

  /**
   * The email account category.
   */
  EMAIL_ACCOUNT,

  /**
   * The social security number category.
   */
  SOCIAL_SECURITY_NUMBER,

  /**
   * The API credential category.
   */
  API_CREDENTIAL,

  /**
   * The custom category, used as the default for any unknown category.
   */
  @JsonEnumDefaultValue
  CUSTOM
}

package com.sanctionco.connect.model;

public enum PatchOperation {
  ADD("add"),
  REMOVE("remove"),
  REPLACE("replace");

  private final String value;

  PatchOperation(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}

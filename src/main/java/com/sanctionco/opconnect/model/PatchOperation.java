package com.sanctionco.opconnect.model;

/**
 * Represents all possible patch operations that can be used when
 * updating an item's details in
 * {@link com.sanctionco.opconnect.OPConnectClient#patchItem(String, String, Patch...)}.
 */
public enum PatchOperation {
  /**
   * The "add" operation. Used to add a value at a path.
   */
  ADD("add"),

  /**
   * The "remove" operation. Used to remove a value at a path.
   */
  REMOVE("remove"),

  /**
   * The "replace" operation. Used to replace the value at a path.
   */
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

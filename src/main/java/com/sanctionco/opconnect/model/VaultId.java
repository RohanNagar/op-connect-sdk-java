package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class VaultId {
  private final String id;

  @JsonCreator
  public VaultId(@JsonProperty("id") String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    VaultId vaultId = (VaultId) o;
    return Objects.equals(id, vaultId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", VaultId.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .toString();
  }
}

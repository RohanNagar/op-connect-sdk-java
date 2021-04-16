package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Patch {
  private final PatchOperation op;
  private final String path;
  private final Object value;

  public Patch(PatchOperation op, String path) {
    this(op, path, null);
  }

  @JsonCreator
  public Patch(@JsonProperty("op") PatchOperation op,
               @JsonProperty("path") String path,
               @JsonProperty("value") Object value) {
    this.op = op;
    this.path = path;
    this.value = value;
  }

  public PatchOperation getOp() {
    return op;
  }

  public String getPath() {
    return path;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Patch patch = (Patch) o;
    return op == patch.op && Objects.equals(path, patch.path) && Objects.equals(value, patch.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(op, path, value);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Patch.class.getSimpleName() + "[", "]")
        .add("op=" + op)
        .add("path='" + path + "'")
        .add("value=" + value)
        .toString();
  }
}

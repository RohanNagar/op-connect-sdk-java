package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;
import java.util.StringJoiner;

@JsonDeserialize(builder = Patch.Builder.class)
public class Patch {
  private final PatchOperation op;
  private final String path;
  private final Object value;

  private Patch(PatchOperation op, String path, Object value) {
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
    return op == patch.op
        && Objects.equals(path, patch.path)
        && Objects.equals(value, patch.value);
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

  public static Builder builder() {
    return new Builder();
  }

  public static Builder add() {
    return builder().withOp(PatchOperation.ADD);
  }

  public static Builder remove() {
    return builder().withOp(PatchOperation.REMOVE);
  }

  public static Builder replace() {
    return builder().withOp(PatchOperation.REPLACE);
  }

  public static class Builder {
    private PatchOperation op;
    private String path;
    private Object value;

    public Builder withOp(PatchOperation op) {
      this.op = op;
      return this;
    }

    public Builder withPath(String path) {
      this.path = path;
      return this;
    }

    public Builder withValue(Object value) {
      this.value = value;
      return this;
    }

    public Patch build() {
      return new Patch(op, path, value);
    }
  }
}

package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Id {
  private final String id;
  private final String name;

  @JsonCreator
  public Id(@JsonProperty("id") String id,
            @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Id id = (Id) o;
    return Objects.equals(this.id, id.id) && Objects.equals(this.name, id.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Id.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("name='" + name + "'")
        .toString();
  }
}

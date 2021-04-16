package com.sanctionco.connect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Section {
  private final String id;
  private final String label;

  @JsonCreator
  public Section(@JsonProperty("id") String id,
                 @JsonProperty("label") String label) {
    this.id = id;
    this.label = label;
  }

  public String getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Section section = (Section) o;
    return id.equals(section.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Section.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("label='" + label + "'")
        .toString();
  }
}

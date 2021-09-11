package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a section within an {@link Item}.
 */
public class Section {
  private final String id;
  private final String label;

  @JsonCreator
  public Section(@JsonProperty("id") String id,
                 @JsonProperty("label") String label) {
    this.id = id;
    this.label = label;
  }

  /**
   * Get the ID of this section.
   *
   * @return the ID of this section
   */
  public String getId() {
    return id;
  }

  /**
   * Get the label of this section.
   *
   * @return the label of this section
   */
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

package com.sanctionco.connect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Field {
  private final String id;
  private final Purpose purpose;
  private final Type type;
  private final String label;
  private final String value;
  private final Boolean generate;
  private final GeneratorRecipe recipe;
  private final Section section;
  private final Double entropy;

  @JsonCreator
  public Field(@JsonProperty("id") String id,
               @JsonProperty("purpose") Purpose purpose,
               @JsonProperty("type") Type type,
               @JsonProperty("label") String label,
               @JsonProperty("value") String value,
               @JsonProperty("generate") Boolean generate,
               @JsonProperty("recipe") GeneratorRecipe recipe,
               @JsonProperty("section") Section section,
               @JsonProperty("entropy") Double entropy) {
    this.id = id;
    this.purpose = purpose;
    this.type = type;
    this.label = label;
    this.value = value;
    this.generate = generate;
    this.recipe = recipe;
    this.section = section;
    this.entropy = entropy;
  }

  public String getId() {
    return id;
  }

  public Purpose getPurpose() {
    return purpose;
  }

  public Type getType() {
    return type;
  }

  public String getLabel() {
    return label;
  }

  public String getValue() {
    return value;
  }

  public Boolean getGenerate() {
    return generate;
  }

  public GeneratorRecipe getRecipe() {
    return recipe;
  }

  public Section getSection() {
    return section;
  }

  public Double getEntropy() {
    return entropy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Field field = (Field) o;
    return Objects.equals(id, field.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Field.class.getSimpleName() + "[", "]")
        .add("id='" + id + "'")
        .add("purpose=" + purpose)
        .add("type=" + type)
        .add("label='" + label + "'")
        .add("value='" + value + "'")
        .add("generate=" + generate)
        .add("recipe=" + recipe)
        .add("section=" + section)
        .add("entropy=" + entropy)
        .toString();
  }
}

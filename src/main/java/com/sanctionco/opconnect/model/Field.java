package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;
import java.util.StringJoiner;

@JsonDeserialize(builder = Field.Builder.class)
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

  private Field(Builder builder) {
    this.id = builder.id;
    this.purpose = builder.purpose;
    this.type = builder.type;
    this.label = builder.label;
    this.value = builder.value;
    this.generate = builder.generate;
    this.recipe = builder.recipe;
    this.section = builder.section;
    this.entropy = builder.entropy;
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

  public static Builder username(String username) {
    return builder().withPurpose(Purpose.USERNAME).withValue(username);
  }

  public static Builder password(String password) {
    return builder().withPurpose(Purpose.PASSWORD).withValue(password);
  }

  public static Builder generatedPassword() {
    return builder().withPurpose(Purpose.PASSWORD).withGenerate(true);
  }

  public static Builder generatedPassword(GeneratorRecipe recipe) {
    return builder().withPurpose(Purpose.PASSWORD).withGenerate(true)
        .withRecipe(recipe);
  }

  public static Builder labeled(String label) {
    return builder().withLabel(label);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String id;
    private Purpose purpose;
    private Type type;
    private String label;
    private String value;
    private Boolean generate;
    private GeneratorRecipe recipe;
    private Section section;
    private Double entropy;

    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    public Builder withPurpose(Purpose purpose) {
      this.purpose = purpose;
      return this;
    }

    public Builder withType(Type type) {
      this.type = type;
      return this;
    }

    public Builder withLabel(String label) {
      this.label = label;
      return this;
    }

    public Builder withValue(String value) {
      this.value = value;
      return this;
    }

    public Builder withGenerate(Boolean generate) {
      this.generate = generate;
      return this;
    }

    public Builder withRecipe(GeneratorRecipe recipe) {
      this.recipe = recipe;
      return this;
    }

    public Builder withSection(Section section) {
      this.section = section;
      return this;
    }

    public Builder withEntropy(Double entropy) {
      this.entropy = entropy;
      return this;
    }

    public Field build() {
      return new Field(this);
    }
  }
}

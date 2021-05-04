package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

  private Field(String id,
                Purpose purpose,
                Type type,
                String label,
                String value,
                Boolean generate,
                GeneratorRecipe recipe,
                Section section,
                Double entropy) {
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

  public static Builder username(String username) {
    return builder().withPurpose(Purpose.USERNAME).withValue(username);
  }

  public static Builder password(String password) {
    return builder().withPurpose(Purpose.PASSWORD).withValue(password);
  }

  public static Builder generatedPassword() {
    return builder().withPurpose(Purpose.PASSWORD).withGenerate(true);
  }

  public static Builder generatedPassword(int length) {
    return builder().withPurpose(Purpose.PASSWORD).withGenerate(true)
        .withRecipe(new GeneratorRecipe(length, Arrays.asList(CharacterSet.values())));
  }

  public static Builder generatedPassword(int length, List<CharacterSet> allowedCharacters) {
    return builder().withPurpose(Purpose.PASSWORD).withGenerate(true)
        .withRecipe(new GeneratorRecipe(length, new ArrayList<>(allowedCharacters)));
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
      return new Field(id, purpose, type, label, value, generate, recipe, section, entropy);
    }
  }
}

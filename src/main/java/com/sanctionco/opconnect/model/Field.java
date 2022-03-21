package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a field contained in an {@link Item}.
 */
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
  private final PasswordDetails passwordDetails;
  private final String totp;

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
    this.passwordDetails = builder.passwordDetails;
    this.totp = builder.totp;
  }

  /**
   * Get the ID of this field.
   *
   * @return the ID of the field
   */
  public String getId() {
    return id;
  }

  /**
   * Get the {@link Purpose} of this field.
   *
   * @return the purpose of the field
   */
  public Purpose getPurpose() {
    return purpose;
  }

  /**
   * Get the {@link Type} of this field.
   *
   * @return the type of the field
   */
  public Type getType() {
    return type;
  }

  /**
   * Get the label of this field.
   *
   * @return the label of this field
   */
  public String getLabel() {
    return label;
  }

  /**
   * Get the value of this field.
   *
   * @return the value of this field
   */
  public String getValue() {
    return value;
  }

  /**
   * Get whether this field should have a generated value. This will not
   * be set for fields that are returned from 1Password Connect, as the
   * {@code generate} option is only used when creating a field.
   *
   * @return true if the value should be generated, false otherwise
   */
  public Boolean getGenerate() {
    return generate;
  }

  /**
   * Get the {@link GeneratorRecipe} to use when generating the value, if {@link #getGenerate()}
   * is set to {@code true}. This will not be set for fields that are returned from 1Password
   * Connect, as the {@code generate} option is only used when creating a field.
   *
   * @return the {@link GeneratorRecipe} to use when generating the value
   */
  public GeneratorRecipe getRecipe() {
    return recipe;
  }

  /**
   * Get the {@link Section} that this field is contained in.
   *
   * @return the section this field is contained in
   */
  public Section getSection() {
    return section;
  }

  /**
   * Get the entropy of the generated field value.
   *
   * @return the entropy of the generated field value
   */
  public Double getEntropy() {
    return entropy;
  }

  /**
   * Get the password details for this field if it is a password field.
   *
   * @return the password details for this field if it is a password field, or null otherwise
   */
  @JsonProperty("password_details")
  public PasswordDetails getPasswordDetails() {
    return passwordDetails;
  }

  /**
   * Get the TOTP value for this field if it is an OTP field.
   *
   * @return the current TOTP value for this field if it is an OTP field, or null otherwise
   */
  public String getTotp() {
    return totp;
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
        .add("passwordDetails=" + passwordDetails)
        .add("totp=" + totp)
        .toString();
  }

  /**
   * Create a new {@link Field.Builder} for a username field.
   *
   * @param username the value of the username to set
   * @return a new builder instance used to build a {@link Field}
   */
  public static Builder username(String username) {
    return builder().withPurpose(Purpose.USERNAME).withValue(username);
  }

  /**
   * Create a new {@link Field.Builder} for a password field.
   *
   * @param password the value of the password to set
   * @return a new builder instance used to build a {@link Field}
   */
  public static Builder password(String password) {
    return builder().withPurpose(Purpose.PASSWORD).withValue(password);
  }

  /**
   * Create a new {@link Field.Builder} for a generated password field.
   *
   * @return a new builder instance used to build a {@link Field}
   */
  public static Builder generatedPassword() {
    return builder().withPurpose(Purpose.PASSWORD).withGenerate(true);
  }

  /**
   * Create a new {@link Field.Builder} for a generated password field.
   *
   * @param recipe the {@link GeneratorRecipe} to use when generating the password value
   * @return a new builder instance used to build a {@link Field}
   */
  public static Builder generatedPassword(GeneratorRecipe recipe) {
    return builder().withPurpose(Purpose.PASSWORD).withGenerate(true)
        .withRecipe(recipe);
  }

  /**
   * Create a new {@link Field.Builder} for a note field.
   *
   * @param note the value of the note to set
   * @return a new builder instance used to build a {@link Field}
   */
  public static Builder note(String note) {
    return builder().withPurpose(Purpose.NOTES).withValue(note);
  }

  /**
   * Create a new {@link Field.Builder} for a field with the given label.
   *
   * @param label the label to use for the field
   * @return a new builder instance used to build a {@link Field}
   */
  public static Builder labeled(String label) {
    return builder().withLabel(label);
  }

  /**
   * Create a new {@link Field.Builder} with no properties set.
   *
   * @return a new builder instance used to build a {@link Field}
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * The builder class used to build a new {@link Field}.
   */
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
    private PasswordDetails passwordDetails;
    private String totp;

    /**
     * Set the ID of the field.
     *
     * @param id the ID of the field
     * @return this
     */
    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    /**
     * Set the {@link Purpose} of the field.
     *
     * @param purpose the purpose of the field
     * @return this
     */
    public Builder withPurpose(Purpose purpose) {
      this.purpose = purpose;
      return this;
    }

    /**
     * Set the {@link Type} of the field.
     *
     * @param type the type of the field
     * @return this
     */
    public Builder withType(Type type) {
      this.type = type;
      return this;
    }

    /**
     * Set the label for the field.
     *
     * @param label the label of the field
     * @return this
     */
    public Builder withLabel(String label) {
      this.label = label;
      return this;
    }

    /**
     * Set the value of the field.
     *
     * @param value the value of the field
     * @return this
     */
    public Builder withValue(String value) {
      this.value = value;
      return this;
    }

    /**
     * Set to true in order to generate the value of the field.
     *
     * @param generate true to generate the value, or false otherwise
     * @return this
     */
    public Builder withGenerate(Boolean generate) {
      this.generate = generate;
      return this;
    }

    /**
     * Set that the value of the field should be generated.
     *
     * @return this
     */
    public Builder generate() {
      return withGenerate(true);
    }

    /**
     * Set the {@link GeneratorRecipe} to use when generating the value of the field.
     *
     * @param recipe the recipe to use when generating the value
     * @return this
     */
    public Builder withRecipe(GeneratorRecipe recipe) {
      this.recipe = recipe;
      return this;
    }

    /**
     * Set the {@link Section} that this field should be a part of.
     *
     * @param section the section that the field belongs to
     * @return this
     */
    public Builder withSection(Section section) {
      this.section = section;
      return this;
    }

    /**
     * Set the entropy of the generated value.
     *
     * @param entropy the entropy of the generated value
     * @return this
     */
    public Builder withEntropy(Double entropy) {
      this.entropy = entropy;
      return this;
    }

    /**
     * Set the {@link PasswordDetails} when this field is a password field.
     *
     * @param passwordDetails the password details
     * @return this
     */
    @JsonProperty("password_details")
    @JsonAlias("passwordDetails")
    public Builder withPasswordDetails(PasswordDetails passwordDetails) {
      this.passwordDetails = passwordDetails;
      return this;
    }

    /**
     * Set the current TOTP value when this field is an OTP type field.
     *
     * @param totp the current TOTP value
     * @return this
     */
    public Builder withTotp(String totp) {
      this.totp = totp;
      return this;
    }

    /**
     * Build a new {@link Field} instance based on the current configuration of
     * the builder.
     *
     * @return a new {@link Field} instance
     */
    public Field build() {
      return new Field(this);
    }
  }
}

package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Contains the password details for a password {@link Field}.
 */
public class PasswordDetails {
  private final Double entropy;
  private final Boolean generated;
  private final String strength; // TERRIBLE, WEAK, FAIR, GOOD, VERY_GOOD, EXCELLENT, FANTASTIC
  private final List<String> history;

  @JsonCreator
  public PasswordDetails(@JsonProperty("entropy") Double entropy,
                         @JsonProperty("generated") Boolean generated,
                         @JsonProperty("strength") String strength,
                         @JsonProperty("history") List<String> history) {
    this.entropy = entropy;
    this.generated = generated;
    this.strength = strength;
    this.history = history;
  }

  /**
   * Get the entropy of the password if generated.
   *
   * @return the entropy of the password if generated
   */
  public Double getEntropy() {
    return entropy;
  }

  /**
   * Get weather the password was generated or not.
   *
   * @return true if the password was generated, false otherwise
   */
  public Boolean getGenerated() {
    return generated;
  }

  /**
   * Get the strength of the password.
   *
   * @return the strength of the password
   */
  public String getStrength() {
    return strength;
  }

  /**
   * Get the historical values of the password.
   *
   * @return the historical values of the password
   */
  public List<String> getHistory() {
    return history;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PasswordDetails that = (PasswordDetails) o;
    return Objects.equals(strength, that.strength);
  }

  @Override
  public int hashCode() {
    return Objects.hash(strength);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", PasswordDetails.class.getSimpleName() + "[", "]")
        .add("entropy=" + entropy)
        .add("generated=" + generated)
        .add("strength='" + strength + "'")
        .add("history=" + history)
        .toString();
  }
}

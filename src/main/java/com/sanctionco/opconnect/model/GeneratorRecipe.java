package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Represents a recipe for generating a password.
 */
public class GeneratorRecipe {
  private final Integer length;
  private final List<CharacterSet> characterSets;

  @JsonCreator
  GeneratorRecipe(@JsonProperty("length") Integer length,
                  @JsonProperty("characterSets") List<CharacterSet> characterSets) {
    this.length = length;
    this.characterSets = Collections.unmodifiableList(characterSets);
  }

  /**
   * Get the length of the recipe.
   *
   * @return the length
   */
  public Integer getLength() {
    return length;
  }

  /**
   * Get the list of allowed characters.
   *
   * @return the list of allowed characters
   */
  public List<CharacterSet> getCharacterSets() {
    return characterSets;
  }

  /**
   * Create a new {@code GeneratorRecipe.Builder} with the given list of characters.
   *
   * @param characters the characters to include in generated passwords
   * @return the new Builder
   */
  public static Builder withAllowedCharacters(List<CharacterSet> characters) {
    return new Builder(characters);
  }

  /**
   * Create a new {@code GeneratorRecipe.Builder} with only letter characters allowed.
   *
   * @return the new Builder
   */
  public static Builder letters() {
    return new Builder(CharacterSet.letters());
  }

  /**
   * Create a new {@code GeneratorRecipe.Builder} with only digit characters allowed.
   *
   * @return the new Builder
   */
  public static Builder digits() {
    return new Builder(CharacterSet.digits());
  }

  /**
   * Create a new {@code GeneratorRecipe.Builder} with only symbol characters allowed.
   *
   * @return the new Builder
   */
  public static Builder symbols() {
    return new Builder(CharacterSet.symbols());
  }

  /**
   * Create a new {@code GeneratorRecipe.Builder} with letters and digits allowed.
   *
   * @return the new Builder
   */
  public static Builder lettersAndDigits() {
    return new Builder(CharacterSet.lettersAndDigits());
  }

  /**
   * Create a new {@code GeneratorRecipe.Builder} with letters and symbols allowed.
   *
   * @return the new Builder
   */
  public static Builder lettersAndSymbols() {
    return new Builder(CharacterSet.lettersAndSymbols());
  }

  /**
   * Create a new {@code GeneratorRecipe.Builder} with digits and symbols allowed.
   *
   * @return the new Builder
   */
  public static Builder digitsAndSymbols() {
    return new Builder(CharacterSet.digitsAndSymbols());
  }

  /**
   * Create a new {@code GeneratorRecipe.Builder} with all characters allowed.
   *
   * @return the new Builder
   */
  public static Builder allCharacters() {
    return new Builder(CharacterSet.allCharacters());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GeneratorRecipe that = (GeneratorRecipe) o;
    return Objects.equals(length, that.length)
        && Objects.equals(characterSets, that.characterSets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(length, characterSets);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GeneratorRecipe.class.getSimpleName() + "[", "]")
        .add("length=" + length)
        .add("characterSets=" + characterSets)
        .toString();
  }

  /**
   * The builder class used to build a new {@link GeneratorRecipe}.
   */
  public static class Builder {
    private final List<CharacterSet> characters;

    Builder(List<CharacterSet> characters) {
      this.characters = characters;
    }

    /**
     * Create a new {@code GeneratorRecipe} with the given length.
     *
     * @param length the required length for generated passwords
     * @return a new {@code GeneratorRecipe} instance
     */
    public GeneratorRecipe ofLength(int length) {
      return new GeneratorRecipe(length, characters);
    }
  }
}

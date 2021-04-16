package com.sanctionco.opconnect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class GeneratorRecipe {
  private final Integer length;
  private final List<CharacterSet> characterSets;

  @JsonCreator
  public GeneratorRecipe(@JsonProperty("length") Integer length,
                         @JsonProperty("characterSets") List<CharacterSet> characterSets) {
    this.length = length;
    this.characterSets = characterSets;
  }

  public Integer getLength() {
    return length;
  }

  public List<CharacterSet> getCharacterSets() {
    return characterSets;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GeneratorRecipe that = (GeneratorRecipe) o;
    return Objects.equals(length, that.length) && Objects.equals(characterSets, that.characterSets);
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
}

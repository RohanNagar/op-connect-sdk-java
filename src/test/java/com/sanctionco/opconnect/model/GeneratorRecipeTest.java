package com.sanctionco.opconnect.model;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneratorRecipeTest {

  @ParameterizedTest(name = "{index} Recipe of length 12 and characters {1}")
  @MethodSource("provider")
  void shouldBuild(GeneratorRecipe recipe, List<CharacterSet> expectedCharacters) {
    assertEquals(12, recipe.getLength());
    assertEquals(expectedCharacters.size(), recipe.getCharacterSets().size());

    expectedCharacters.forEach(set -> assertTrue(recipe.getCharacterSets().contains(set)));
  }

  @SuppressWarnings("unused")
  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(GeneratorRecipe.letters().ofLength(12), CharacterSet.letters()),
        Arguments.of(GeneratorRecipe.digits().ofLength(12), CharacterSet.digits()),
        Arguments.of(GeneratorRecipe.symbols().ofLength(12), CharacterSet.symbols()),
        Arguments.of(
            GeneratorRecipe.lettersAndDigits().ofLength(12),
            CharacterSet.lettersAndDigits()),
        Arguments.of(
            GeneratorRecipe.lettersAndSymbols().ofLength(12),
            CharacterSet.lettersAndSymbols()),
        Arguments.of(
            GeneratorRecipe.digitsAndSymbols().ofLength(12),
            CharacterSet.digitsAndSymbols()),
        Arguments.of(
            GeneratorRecipe.allCharacters().ofLength(12),
            CharacterSet.allCharacters()),
        Arguments.of(
            GeneratorRecipe.withAllowedCharacters(CharacterSet.digitsAndSymbols()).ofLength(12),
            CharacterSet.digitsAndSymbols()));
  }

  @Test
  void hashCodeAndEqualsShouldWork() {
    GeneratorRecipe recipe = GeneratorRecipe.letters().ofLength(10);
    GeneratorRecipe sameRecipe = GeneratorRecipe.letters().ofLength(10);
    GeneratorRecipe diffLength = GeneratorRecipe.letters().ofLength(15);
    GeneratorRecipe diffCharacters = GeneratorRecipe.symbols().ofLength(10);

    assertAll("Equals and hashcode works for same properties",
        () -> assertEquals(recipe.hashCode(), sameRecipe.hashCode()),
        () -> assertEquals(recipe, sameRecipe),
        () -> assertEquals(recipe.toString(), sameRecipe.toString()));

    assertAll("Equals and hashcode works for different length property",
        () -> assertNotEquals(recipe.hashCode(), diffLength.hashCode()),
        () -> assertNotEquals(recipe, diffLength),
        () -> assertNotEquals(recipe.toString(), diffLength.toString()));

    assertAll("Equals and hashcode works for different characterSet property",
        () -> assertNotEquals(recipe.hashCode(), diffCharacters.hashCode()),
        () -> assertNotEquals(recipe, diffCharacters),
        () -> assertNotEquals(recipe.toString(), diffCharacters.toString()));
  }
}

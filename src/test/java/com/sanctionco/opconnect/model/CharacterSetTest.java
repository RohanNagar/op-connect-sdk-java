package com.sanctionco.opconnect.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharacterSetTest {

  @ParameterizedTest(name = "{index} List of {1}")
  @MethodSource("provider")
  void listsShouldBeCorrect(List<CharacterSet> created, List<CharacterSet> expected) {
    assertEquals(expected.size(), created.size());

    expected.forEach(set -> assertTrue(created.contains(set)));
  }

  @SuppressWarnings("unused")
  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(CharacterSet.letters(), Collections.singletonList(CharacterSet.LETTERS)),
        Arguments.of(CharacterSet.digits(), Collections.singletonList(CharacterSet.DIGITS)),
        Arguments.of(CharacterSet.symbols(), Collections.singletonList(CharacterSet.SYMBOLS)),
        Arguments.of(
            CharacterSet.lettersAndDigits(),
            Arrays.asList(CharacterSet.LETTERS, CharacterSet.DIGITS)),
        Arguments.of(
            CharacterSet.lettersAndSymbols(),
            Arrays.asList(CharacterSet.LETTERS, CharacterSet.SYMBOLS)),
        Arguments.of(
            CharacterSet.digitsAndSymbols(),
            Arrays.asList(CharacterSet.DIGITS, CharacterSet.SYMBOLS)),
        Arguments.of(
            CharacterSet.allCharacters(),
            Arrays.asList(CharacterSet.LETTERS, CharacterSet.DIGITS, CharacterSet.SYMBOLS)));
  }
}

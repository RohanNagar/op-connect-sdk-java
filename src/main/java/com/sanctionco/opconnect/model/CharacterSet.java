package com.sanctionco.opconnect.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents all allowed types of character sets.
 */
public enum CharacterSet {
  /**
   * The character set consisting of only letters.
   */
  LETTERS,

  /**
   * The character set consisting of only numerical digits.
   */
  DIGITS,

  /**
   * The character set consisting of only symbols.
   */
  SYMBOLS;

  /**
   * Returns a list of {@code CharacterSet} containing only letters.
   *
   * @return the new {@link List}
   */
  public static List<CharacterSet> letters() {
    return Collections.singletonList(LETTERS);
  }

  /**
   * Returns a list of {@code CharacterSet} containing only digits.
   *
   * @return the new {@link List}
   */
  public static List<CharacterSet> digits() {
    return Collections.singletonList(DIGITS);
  }

  /**
   * Returns a list of {@code CharacterSet} containing only symbols.
   *
   * @return the new {@link List}
   */
  public static List<CharacterSet> symbols() {
    return Collections.singletonList(SYMBOLS);
  }

  /**
   * Returns a list of {@code CharacterSet} containing letters and digits.
   *
   * @return the new {@link List}
   */
  public static List<CharacterSet> lettersAndDigits() {
    return Arrays.asList(LETTERS, DIGITS);
  }

  /**
   * Returns a list of {@code CharacterSet} containing letters and symbols.
   *
   * @return the new {@link List}
   */
  public static List<CharacterSet> lettersAndSymbols() {
    return Arrays.asList(LETTERS, SYMBOLS);
  }

  /**
   * Returns a list of {@code CharacterSet} containing digits and symbols.
   *
   * @return the new {@link List}
   */
  public static List<CharacterSet> digitsAndSymbols() {
    return Arrays.asList(DIGITS, SYMBOLS);
  }

  /**
   * Returns a list of {@code CharacterSet} containing all characters.
   *
   * @return the new {@link List}
   */
  public static List<CharacterSet> allCharacters() {
    return Arrays.asList(values());
  }
}

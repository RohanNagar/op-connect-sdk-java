package com.sanctionco.opconnect.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldTest {

  @Test
  void shouldBuildUsername() {
    Field field = Field.username("testname").withLabel("username").build();

    assertAll("Field properties are correct",
        () -> assertEquals(Purpose.USERNAME, field.getPurpose()),
        () -> assertEquals("testname", field.getValue()),
        () -> assertEquals("username", field.getLabel()));
  }

  @Test
  void shouldBuildPassword() {
    Field field = Field.password("pass").withLabel("password").build();

    assertAll("Field properties are correct",
        () -> assertEquals(Purpose.PASSWORD, field.getPurpose()),
        () -> assertEquals("pass", field.getValue()),
        () -> assertEquals("password", field.getLabel()));
  }

  @Test
  void shouldBuildGeneratedPassword() {
    Field field = Field.generatedPassword().withLabel("password").build();

    assertAll("Field properties are correct",
        () -> assertEquals(Purpose.PASSWORD, field.getPurpose()),
        () -> assertTrue(field.getGenerate()),
        () -> assertEquals("password", field.getLabel()));
  }

  @Test
  void shouldBuildGeneratedPasswordWithRecipe() {
    Field field = Field.generatedPassword(GeneratorRecipe.letters().ofLength(30))
        .withLabel("password")
        .build();

    assertAll("Field properties are correct",
        () -> assertEquals(Purpose.PASSWORD, field.getPurpose()),
        () -> assertTrue(field.getGenerate()),
        () -> assertEquals(GeneratorRecipe.letters().ofLength(30), field.getRecipe()),
        () -> assertEquals("password", field.getLabel()));
  }

  @Test
  void shouldBuildNote() {
    Field field = Field.note("My Note Contents").withLabel("note").build();

    assertAll("Field properties are correct",
        () -> assertEquals(Purpose.NOTES, field.getPurpose()),
        () -> assertEquals("My Note Contents", field.getValue()),
        () -> assertEquals("note", field.getLabel()));
  }
}

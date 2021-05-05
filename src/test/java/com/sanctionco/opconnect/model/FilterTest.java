package com.sanctionco.opconnect.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterTest {

  @Test
  void shouldBuildContainsFilter() {
    Filter filter = Filter.title().contains("test");

    assertEquals("title co \"test\"", filter.getFilter());
  }

  @Test
  void shouldBuildEqualsFilter() {
    Filter filter = Filter.title().equals("test");

    assertEquals("title eq \"test\"", filter.getFilter());
  }

  @Test
  void shouldBuildStartsWithFilter() {
    Filter filter = Filter.title().startsWith("test");

    assertEquals("title sw \"test\"", filter.getFilter());
  }

  @Test
  void shouldBuildPresentFilter() {
    Filter filter = Filter.title().present();

    assertEquals("title pr", filter.getFilter());
  }

  @Test
  void shouldBuildGreaterThanFilter() {
    Filter filter = Filter.title().greaterThan("a");

    assertEquals("title gt \"a\"", filter.getFilter());
  }

  @Test
  void shouldBuildGreaterThanOrEqualFilter() {
    Filter filter = Filter.title().greaterThanOrEqual("a");

    assertEquals("title ge \"a\"", filter.getFilter());
  }

  @Test
  void shouldBuildLessThanFilter() {
    Filter filter = Filter.title().lessThan("a");

    assertEquals("title lt \"a\"", filter.getFilter());
  }

  @Test
  void shouldBuildLessThanOrEqualFilter() {
    Filter filter = Filter.title().lessThanOrEqual("a");

    assertEquals("title le \"a\"", filter.getFilter());
  }

  @Test
  void shouldBuildFilterWithAnd() {
    Filter filter = Filter.title().contains("test")
        .and(Filter.title().equals("other"));

    assertEquals("(title co \"test\" and title eq \"other\")", filter.getFilter());
  }

  @Test
  void shouldBuildFilterWithOr() {
    Filter filter = Filter.title().contains("test")
        .or(Filter.title().equals("other"));

    assertEquals("(title co \"test\" or title eq \"other\")", filter.getFilter());
  }

  @Test
  void shouldBuildFilterWithAndOr() {
    Filter filter = Filter.title().contains("test")
        .and(Filter.title().equals("other")
            .or(Filter.title().startsWith("te")));

    assertEquals("(title co \"test\" and (title eq \"other\" or title sw \"te\"))",
        filter.getFilter());
  }
}

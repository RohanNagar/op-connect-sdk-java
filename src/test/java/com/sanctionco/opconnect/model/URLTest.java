package com.sanctionco.opconnect.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class URLTest {

  @Test
  void shouldBuildPrimaryUrl() {
    URL url = URL.primary("https://www.test.com");

    assertTrue(url.getPrimary());
    assertEquals("https://www.test.com", url.getUrl());
  }

  @Test
  void shouldBuildNonPrimaryUrl() {
    URL url = URL.standard("https://www.test.com");

    assertFalse(url.getPrimary());
    assertEquals("https://www.test.com", url.getUrl());
  }

  @Test
  void hashCodeAndEqualsShouldWork() {
    URL url = URL.primary("https://www.test.com");
    URL sameUrl = URL.primary("https://www.test.com");
    URL diffStringUrl = URL.primary("https://www.diff.com");
    URL diffPrimaryUrl = URL.standard("https://www.test.com");

    assertAll("Equals and hashcode works for same properties",
        () -> assertEquals(url.hashCode(), sameUrl.hashCode()),
        () -> assertEquals(url, sameUrl),
        () -> assertEquals(url.toString(), sameUrl.toString()));

    assertAll("Equals and hashcode works for different url property",
        () -> assertNotEquals(url.hashCode(), diffStringUrl.hashCode()),
        () -> assertNotEquals(url, diffStringUrl),
        () -> assertNotEquals(url.toString(), diffStringUrl.toString()));

    assertAll("Equals and hashcode works for different primary property",
        () -> assertNotEquals(url.hashCode(), diffPrimaryUrl.hashCode()),
        () -> assertNotEquals(url, diffPrimaryUrl),
        () -> assertNotEquals(url.toString(), diffPrimaryUrl.toString()));
  }
}

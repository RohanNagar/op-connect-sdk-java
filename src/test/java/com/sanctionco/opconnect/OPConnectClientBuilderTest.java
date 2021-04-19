package com.sanctionco.opconnect;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OPConnectClientBuilderTest {

  @Test
  void buildShouldThrowWithoutSettingEndpoint() {
    NullPointerException e = assertThrows(NullPointerException.class,
        () -> OPConnectClientBuilder.builder().build());

    assertTrue(e.getMessage().startsWith("You must provide an endpoint"));
  }

  @Test
  void buildShouldThrowWithoutSettingAccessToken() {
    NullPointerException e = assertThrows(NullPointerException.class,
        () -> OPConnectClientBuilder.builder().withEndpoint("test").build());

    assertTrue(e.getMessage().startsWith("You must provide an access token"));
  }

  @Test
  void shouldBuild() {
    OPConnectClientBuilder.builder()
        .withEndpoint("https://endpoint")
        .withAccessToken("token")
        .build();
  }

  @Test
  void shouldBuildWithTimeout() {
    OPConnectClientBuilder.builder()
        .withEndpoint("https://endpoint")
        .withAccessToken("token")
        .withTimeoutInMilliseconds(5000L)
        .build();
  }

  @Test
  void testEnsureTrailingSlashExistsNoChange() {
    String url = "https://www.sanctionco.com/";
    String result = OPConnectClientBuilder.ensureTrailingSlashExists(url);

    assertEquals(url, result);
  }

  @Test
  void testEnsureTrailingSlashExistsNoSlash() {
    String url = "https://www.sanctionco.com";
    String result = OPConnectClientBuilder.ensureTrailingSlashExists(url);

    assertNotEquals(url, result);
    assertEquals("https://www.sanctionco.com/", result);
  }
}

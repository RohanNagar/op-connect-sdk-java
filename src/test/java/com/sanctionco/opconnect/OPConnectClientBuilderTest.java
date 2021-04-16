package com.sanctionco.opconnect;

import org.junit.jupiter.api.Test;

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
}

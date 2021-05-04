package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Vault;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("OPConnectClient")
@EnabledIfEnvironmentVariable(named = "OP_ACCESS_TOKEN", matches = ".*")
class IntegrationTest {
  private static final String TOKEN = System.getenv("OP_ACCESS_TOKEN");
  private static final OPConnectClient CLIENT = OPConnectClient.builder()
      .withEndpoint("http://localhost:8080/")
      .withAccessToken(TOKEN)
      .build();

  @Test
  void shouldListSingleVault() {
    List<Vault> vaults = CLIENT.listVaults().join();

    assertEquals(1, vaults.size());

    Vault vault = vaults.get(0);

    assertAll("The vault properties are as expected",
        () -> assertEquals("5ve5wfpdu2kxxhj2jdozmes5re", vault.getId()),
        () -> assertEquals("Integration Test", vault.getName()),
        () -> assertEquals("Java SDK Integration Tests", vault.getDescription()),
        () -> assertTrue(vault.getCreatedAt().isBefore(Instant.now())));
  }

}

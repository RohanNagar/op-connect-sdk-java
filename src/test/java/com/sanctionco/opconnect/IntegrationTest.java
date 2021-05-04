package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Vault;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletionException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import retrofit2.HttpException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("OPConnectClient")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfEnvironmentVariable(named = "OP_ACCESS_TOKEN", matches = ".*")
class IntegrationTest {
  private static final String TOKEN = System.getenv("OP_ACCESS_TOKEN");
  private static final OPConnectClient CLIENT = OPConnectClient.builder()
      .withEndpoint("http://localhost:8080/")
      .withAccessToken(TOKEN)
      .build();

  private static final String VAULT_ID = "5ve5wfpdu2kxxhj2jdozmes5re";

  @Test
  void shouldListSingleVault() {
    List<Vault> vaults = CLIENT.listVaults().join();

    assertEquals(1, vaults.size());

    Vault vault = vaults.get(0);

    assertAll("The vault properties are as expected",
        () -> assertEquals(VAULT_ID, vault.getId()),
        () -> assertEquals("Integration Test", vault.getName()),
        () -> assertEquals("Java SDK Integration Tests", vault.getDescription()),
        () -> assertTrue(vault.getCreatedAt().isBefore(Instant.now())));
  }

  @Test
  void shouldFailToReadUnknownVaultId() {
    CompletionException e = assertThrows(CompletionException.class,
        () -> CLIENT.getVault("DoesNotExist").join());

    assertTrue(e.getCause() instanceof HttpException);

    HttpException httpResponse = (HttpException) e.getCause();

    assertEquals(400, httpResponse.code());
  }

  @Test
  void shouldGetVaultDetails() {
    Vault vault = CLIENT.getVault(VAULT_ID).join();

    assertAll("The vault properties are as expected",
        () -> assertEquals(VAULT_ID, vault.getId()),
        () -> assertEquals("Integration Test", vault.getName()),
        () -> assertEquals("Java SDK Integration Tests", vault.getDescription()),
        () -> assertTrue(vault.getCreatedAt().isBefore(Instant.now())));
  }
}

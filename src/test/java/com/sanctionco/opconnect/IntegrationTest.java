package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Category;
import com.sanctionco.opconnect.model.Filter;
import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Section;
import com.sanctionco.opconnect.model.Vault;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import retrofit2.HttpException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("OPConnectClient")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnabledIfEnvironmentVariable(named = "OP_ACCESS_TOKEN", matches = ".*")
class IntegrationTest {
  private static final String TOKEN = System.getenv("OP_ACCESS_TOKEN");
  private static final OPConnectClient CLIENT = OPConnectClient.builder()
      .withEndpoint("http://localhost:8080/")
      .withAccessToken(TOKEN)
      .build();

  private static final String VAULT_ID = "5ve5wfpdu2kxxhj2jdozmes5re";
  private static final String LOGIN_ITEM_ID = "piy7k3izsuzafhypw6iddpwhqe";
  private static final List<Item> ALL_ITEMS = CLIENT.listItems(VAULT_ID).join();

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
        () -> CLIENT.getVault(LOGIN_ITEM_ID).join());

    assertTrue(e.getCause() instanceof HttpException);

    HttpException httpResponse = (HttpException) e.getCause();

    assertEquals(404, httpResponse.code());
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

  @Test
  void shouldListItems() {
    List<Item> items = CLIENT.listItems(VAULT_ID).join();

    // 19 sample items, each of a different category
    assertEquals(19, items.size());
    items.forEach(item -> assertEquals(VAULT_ID, item.getVault().getId()));

    Set<Category> categories = items.stream().map(Item::getCategory).collect(Collectors.toSet());

    assertEquals(19, categories.size());

    Arrays.stream(Category.values())
        .forEach(category -> {
          // Skip CUSTOM as we don't have that item in the vault
          if (Category.CUSTOM.equals(category)) return;

          assertTrue(categories.contains(category));
        });
  }

  @Test
  void shouldListTitleFilteredItemsUsingString() {
    List<Item> sampleItems = CLIENT.listItems(VAULT_ID, "title co \"Sample\"").join();
    assertEquals(19, sampleItems.size());

    List<Item> passwordItems = CLIENT.listItems(VAULT_ID, "title eq \"Sample Password\"").join();
    assertEquals(1, passwordItems.size());

    List<Item> noItems = CLIENT.listItems(VAULT_ID, "title eq \"Not Exist\"").join();
    assertEquals(0, noItems.size());
  }

  @Test
  void shouldListTitleFilteredItemsUsingFilter() {
    List<Item> sampleItems = CLIENT
        .listItems(VAULT_ID, Filter.title().contains("Sample").build()).join();
    assertEquals(19, sampleItems.size());

    List<Item> passwordItems = CLIENT
        .listItems(VAULT_ID, Filter.title().equals("Sample Password").build()).join();
    assertEquals(1, passwordItems.size());

    List<Item> noItems = CLIENT
        .listItems(VAULT_ID,Filter.title().equals("Not Exist").build()).join();
    assertEquals(0, noItems.size());
  }

  @Test
  void shouldFailToReadUnknownItem() {
    CompletionException e = assertThrows(CompletionException.class,
        () -> CLIENT.getItem(VAULT_ID, VAULT_ID).join());

    assertTrue(e.getCause() instanceof HttpException);

    HttpException httpResponse = (HttpException) e.getCause();

    assertEquals(404, httpResponse.code());
  }

  @Test
  void shouldGetLoginItemDetails() {
    Item item = CLIENT.getItem(VAULT_ID, LOGIN_ITEM_ID).join();
    System.out.println(item);

    assertAll("The item properties are as expected",
        () -> assertEquals("Sample Login", item.getTitle()),
        () -> assertEquals(Category.LOGIN, item.getCategory()),
        () -> assertEquals(1, item.getUrls().size()),
        () -> assertEquals("https://www.example.com", item.getUrls().get(0).getUrl()),
        () -> assertTrue(item.getUrls().get(0).getPrimary()),
        () -> assertTrue(item.getFavorite()),
        () -> assertEquals(2, item.getTags().size()),
        () -> assertFalse(item.getTrashed()),
        () -> assertEquals(2, item.getSections().size()),
        () -> assertTrue(item.getSections().contains(
            new Section("Section_vdb57dmcdx6mej4wpu632j6pru", "Test Section One"))),
        () -> assertEquals(5, item.getFields().size())
    );
  }

  @ParameterizedTest
  @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = { "CUSTOM" })
  void shouldGetItemDetailsForEachCategory(Category category) {
    String id = ALL_ITEMS.stream()
        .filter(item -> category.equals(item.getCategory()))
        .findAny()
        .orElseGet(Assertions::fail)
        .getId();

    Item item = CLIENT.getItem(VAULT_ID, id).join();

    assertAll(
        () -> assertEquals(id, item.getId()),
        () -> assertEquals(category, item.getCategory()));
  }
}

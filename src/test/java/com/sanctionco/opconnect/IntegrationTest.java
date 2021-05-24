package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Category;
import com.sanctionco.opconnect.model.Field;
import com.sanctionco.opconnect.model.Filter;
import com.sanctionco.opconnect.model.GeneratorRecipe;
import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Purpose;
import com.sanctionco.opconnect.model.Section;
import com.sanctionco.opconnect.model.URL;
import com.sanctionco.opconnect.model.Vault;
import com.sanctionco.opconnect.model.apiactivity.APIRequest;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import retrofit2.HttpException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
  private static final Integer CATEGORY_COUNT = Category.values().length - 1;
  private static final List<Item> ALL_ITEMS = CLIENT.listItems(VAULT_ID).join();

  private String createdItemId;

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

    assertEquals(403, httpResponse.code());
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
  @Order(1)
  void shouldListItems() {
    List<Item> items = CLIENT.listItems(VAULT_ID).join();

    // CATEGORY_COUNT sample items, each of a different category
    assertEquals(CATEGORY_COUNT, items.size());
    items.forEach(item -> assertEquals(VAULT_ID, item.getVault().getId()));

    Set<Category> categories = items.stream().map(Item::getCategory).collect(Collectors.toSet());

    assertEquals(CATEGORY_COUNT, categories.size());

    Arrays.stream(Category.values())
        .forEach(category -> {
          // Skip CUSTOM as we don't have that item in the vault
          if (Category.CUSTOM.equals(category)) return;

          assertTrue(categories.contains(category));
        });
  }

  @Test
  @Order(1)
  void shouldListTitleFilteredItemsUsingString() {
    List<Item> sampleItems = CLIENT.listItems(VAULT_ID, "title co \"Sample\"").join();
    assertEquals(CATEGORY_COUNT, sampleItems.size());

    List<Item> passwordItems = CLIENT.listItems(VAULT_ID, "title eq \"Sample Password\"").join();
    assertEquals(1, passwordItems.size());

    List<Item> passwordAndCreditCard = CLIENT
        .listItems(VAULT_ID, "title eq \"Sample Password\" or title co \"Credit\"").join();
    assertEquals(2, passwordAndCreditCard.size());

    List<Item> noItems = CLIENT.listItems(VAULT_ID, "title eq \"Not Exist\"").join();
    assertEquals(0, noItems.size());
  }

  @Test
  @Order(1)
  void shouldListTitleFilteredItemsUsingFilter() {
    List<Item> sampleItems = CLIENT
        .listItems(VAULT_ID, Filter.title().contains("Sample")).join();
    assertEquals(CATEGORY_COUNT, sampleItems.size());

    List<Item> passwordItems = CLIENT
        .listItems(VAULT_ID, Filter.title().equals("Sample Password")).join();
    assertEquals(1, passwordItems.size());

    List<Item> passwordAndCreditCard = CLIENT
        .listItems(VAULT_ID, Filter.title()
            .equals("Sample Password")
            .or(Filter.title().contains("Credit")))
        .join();
    assertEquals(2, passwordAndCreditCard.size());

    List<Item> noItems = CLIENT
        .listItems(VAULT_ID,Filter.title().equals("Not Exist")).join();
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

  @Test
  @Order(2)
  @Disabled
  void shouldCreateItem() {
    Item item = Item.builder().withTitle("Integration Test Created Login")
        .withCategory(Category.LOGIN)
        .withVaultId(VAULT_ID)
        .withField(Field.username("testuser").build())
        .withField(Field.generatedPassword(GeneratorRecipe.letters().ofLength(30)).build())
        .withUrl(URL.primary("https://www.test.com"))
        .build();

    Item created = CLIENT.createItem(VAULT_ID, item).join();

    this.createdItemId = created.getId();

    assertAll("Created Item is as expected",
        () -> assertEquals("Integration Test Created Login", created.getTitle()),
        () -> assertEquals(Category.LOGIN, created.getCategory()),
        () -> assertEquals(3, created.getFields().size()),
        () -> assertEquals(Purpose.USERNAME, created.getFields().get(0).getPurpose()),
        () -> assertEquals("testuser", created.getFields().get(0).getValue()),
        () -> assertEquals(1, created.getUrls().size()),
        () -> assertEquals("https://www.test.com", created.getUrls().get(0).getUrl())
    );
  }

  @Test
  @Order(3)
  @Disabled
  void shouldDeleteItem() throws Exception {
    if (createdItemId == null) fail("The createItem test needs to run before deleteItem");

    // Wait for 1 second in order to make sure the created item exists
    Thread.sleep(1000L);

    System.out.println(createdItemId);
    System.out.println(CLIENT.getItem(VAULT_ID, createdItemId).join());

    assertDoesNotThrow(() -> CLIENT.deleteItem(VAULT_ID, createdItemId).join());
  }

  @Test
  void shouldListApiActivity() {
    List<APIRequest> requests = CLIENT.listAPIActivity().join();

    assertAll("Returned APIRequest list is reasonable",
        () -> assertNotNull(requests),
        () -> assertNotEquals(0, requests.size()),
        () -> assertEquals("FXOLE7IFLBCP7LV4AKGKRYXLYU", requests.get(0).getActor().getId()),
        () -> assertEquals("5R6XDPQ2B5GW3GLDTNKVH7BN6E", requests.get(0).getActor().getAccount()));

    // Limit to last 5
    List<APIRequest> limitedRequests = CLIENT.listAPIActivity(5).join();

    assertAll("Returned limited APIRequest list is reasonable",
        () -> assertNotNull(limitedRequests),
        () -> assertEquals(5, limitedRequests.size()),
        () -> assertEquals("FXOLE7IFLBCP7LV4AKGKRYXLYU",
            limitedRequests.get(0).getActor().getId()),
        () -> assertEquals("5R6XDPQ2B5GW3GLDTNKVH7BN6E",
            limitedRequests.get(0).getActor().getAccount()));

    List<APIRequest> limitedOffsetRequests = CLIENT.listAPIActivity(6, 2).join();

    assertAll("Returned limited APIRequest list is reasonable",
        () -> assertNotNull(limitedOffsetRequests),
        () -> assertEquals(6, limitedOffsetRequests.size()),
        () -> assertNotEquals(
            limitedRequests.get(0).getRequestId(),
            limitedOffsetRequests.get(0).getRequestId()),
        () -> assertEquals(
            limitedRequests.get(2).getRequestId(),
            limitedOffsetRequests.get(0).getRequestId()),
        () -> assertEquals(
            limitedRequests.get(3).getRequestId(),
            limitedOffsetRequests.get(1).getRequestId()));
  }
}

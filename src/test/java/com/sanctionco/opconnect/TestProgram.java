package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Category;
import com.sanctionco.opconnect.model.Field;
import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;
import com.sanctionco.opconnect.model.PatchOperation;
import com.sanctionco.opconnect.model.Vault;
import com.sanctionco.opconnect.model.apiactivity.APIRequest;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TestProgram {
  public static void main(String[] args) throws Exception {
    OPConnectClient client = OPConnectClient.builder()
        .withEndpoint("http://localhost:8080")
        .withAccessToken(System.getenv("OP_ACCESS_TOKEN"))
        .build();

    System.out.println("Running sync test...");
    testSync(client);
    System.out.println();
    System.out.println();

    System.out.println("Running async test...");
    testAsync(client);
    System.out.println();

    System.exit(0);
  }

  static void testSync(OPConnectClient client) throws Exception {
    System.out.println("Getting a list of vaults...");
    List<Vault> vaults = client.listVaults().join();
    System.out.println("Got " + vaults.size() + " vaults: " + vaults);

    System.out.println("Getting vault details for vault " + vaults.get(0).getId());
    Vault vault = client.getVault(vaults.get(0).getId()).join();
    System.out.println("Got details: " + vault);

    OPConnectVaultClient vaultClient = client.getVaultClient(vault.getId());

    System.out.println("Listing items from vault...");
    List<Item> items = vaultClient.listItems().join();
    System.out.println("Got " + items.size() + " items:" + items);

    System.out.println("Listing items that contain 'Hello' in the title...");
    items = vaultClient.listItems("title co \"Hello\"").join();
    System.out.println("Got " + items.size() + " items:" + items);

    System.out.println("Getting details of the first item...");
    Item foundItem = vaultClient.getItem(items.get(0).getId()).join();
    System.out.println("Got item: " + foundItem);

    Item createdItem = Item.builder().withTitle("Test Hello World")
        .withCategory(Category.LOGIN)
        .withVault(vault)
        .withFields(Collections.singletonList(Field.username("myname").build()))
        .build();
    System.out.println("Creating a new item: " + createdItem);
    Item created = vaultClient.createItem(createdItem).join();
    System.out.println("Successfully created: " + created);
    Thread.sleep(2000L);

    System.out.println("Patching item to update title...");
    Patch patch = new Patch(PatchOperation.REPLACE, "/title", "Patched Hello");
    Item patched = vaultClient.patchItem(created.getId(), Collections.singletonList(patch)).join();
    System.out.println("Successfully patched: " + patched);
    Thread.sleep(2000L);

    System.out.println("Replacing item...");
    Item replacingItem = Item.builder().withTitle("Replaced Hello!")
        .withId(patched.getId())
        .withCategory(Category.LOGIN)
        .withVault(vault)
        .withFields(Collections.singletonList(Field.username("myreplacedname").build()))
        .build();
    Item replaced = vaultClient.replaceItem(patched.getId(), replacingItem).join();
    System.out.println("Successfully replaced: " + replaced);
    Thread.sleep(2000L);

    System.out.println("Deleting item...");
    vaultClient.deleteItem(replaced.getId()).join();
    System.out.println("Successfully deleted");

    System.out.println("Getting API activity...");
    List<APIRequest> requests = client.listAPIActivity().join();
    System.out.println("Got " + requests.size() + " activity items:" + requests);

    System.out.println("Getting API activity with limit of 1...");
    requests = client.listAPIActivity(1).join();
    System.out.println("Got " + requests.size() + " activity items:" + requests);

    System.out.println("Getting API activity with offset of 1 and limit of 1...");
    requests = client.listAPIActivity(1, 1).join();
    System.out.println("Got " + requests.size() + " activity items:" + requests);
  }

  static void testAsync(OPConnectClient client) {
    System.out.println("Getting a list of vaults...");
    CompletableFuture<Vault> future  = client.listVaults().thenApply(vaults -> {
      System.out.println("Got " + vaults.size() + " vaults: " + vaults);

      return vaults.get(0).getId();
    }).thenCompose(vaultId -> {
      System.out.println("Getting vault details for vault " + vaultId);

      return client.getVault(vaultId);
    }).whenComplete((vault, throwable) -> System.out.println("Got details: " + vault));

    System.out.println("Sent vault calls.");

    System.out.println("Getting API request lists...");
    final CompletableFuture<List<APIRequest>> apiFuture = client.listAPIActivity()
        .thenCompose(requests -> {
          System.out.println("Got " + requests.size() + " activity items:" + requests);
          return client.listAPIActivity(1);
        }).whenComplete((requests, throwable) ->
        System.out.println("Got " + requests.size() + " activity items:" + requests));

    System.out.println("Sent API calls.");

    System.out.println("Waiting for vault calls..");
    Vault vault = future.join();

    System.out.println("Got vault: " + vault);

    System.out.println("waiting for API calls..");
    apiFuture.join();
  }
}

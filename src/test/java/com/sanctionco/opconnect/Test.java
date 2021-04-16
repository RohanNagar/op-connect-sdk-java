package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.APIRequest;
import com.sanctionco.opconnect.model.Category;
import com.sanctionco.opconnect.model.Field;
import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;
import com.sanctionco.opconnect.model.PatchOperation;
import com.sanctionco.opconnect.model.Purpose;
import com.sanctionco.opconnect.model.Vault;

import java.util.Collections;
import java.util.List;

public class Test {
  public static void main(String[] args) throws Exception {
    OPConnectClient client = OPConnectClientBuilder.builder()
        .withEndpoint("http://localhost:8080")
        .withAccessToken(System.getenv("OP_ACCESS_TOKEN"))
        .build();

    List<Vault> vaults = client.listVaults().join();

    Vault vault = client.getVault(vaults.get(0).getId()).join();
    System.out.println(vault);

    OPConnectVaultClient vaultClient = client.getVaultClient(vault.getId());

    List<Item> items = vaultClient.listItems().join();

    System.out.println(items.size());
    System.out.println(items);

    items = vaultClient.listItems("title co \"Hello\"").join();

    System.out.println(items.size());
    System.out.println(items);

    Item foundItem = vaultClient.getItem(items.get(0).getId()).join();
    System.out.println(foundItem);

    Item createdItem = Item.builder().withTitle("Test Hello World")
        .withCategory(Category.LOGIN)
        .withVault(vault)
        .withFields(Collections.singletonList(
            new Field(null, Purpose.USERNAME, null, null, "myname",
                false, null, null, null)))
        .build();
    System.out.println("Creating: " + createdItem);

    Item created = vaultClient.createItem(createdItem).join();
    System.out.println("Created: " + created);
    Thread.sleep(2000L);

    Patch patch = new Patch(PatchOperation.REPLACE, "/title", "Patched Hello");
    Item patched = vaultClient.patchItem(created.getId(), Collections.singletonList(patch)).join();
    System.out.println("Patched: " + patched);
    Thread.sleep(2000L);

    Item replacingItem = Item.builder().withTitle("Replaced Hello!")
        .withId(patched.getId())
        .withCategory(Category.LOGIN)
        .withVault(vault)
        .withFields(Collections.singletonList(
            new Field(null, Purpose.USERNAME, null, null, "myreplacedname",
                false, null, null, null)))
        .build();
    Item replaced = vaultClient.replaceItem(patched.getId(), replacingItem).join();
    System.out.println("Replaced: " + replaced);
    Thread.sleep(2000L);

    System.out.println("Deleting");
    vaultClient.deleteItem(replaced.getId()).join();
    System.out.println("Done deleting");

    List<APIRequest> requests = client.listAPIActivity().join();
    System.out.println(requests);

    System.exit(0);
  }
}

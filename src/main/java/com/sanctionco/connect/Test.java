package com.sanctionco.connect;

import com.sanctionco.connect.model.Category;
import com.sanctionco.connect.model.Field;
import com.sanctionco.connect.model.Item;
import com.sanctionco.connect.model.Purpose;
import com.sanctionco.connect.model.Vault;

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

    List<Item> items = client.listItems(vaults.get(0).getId()).join();

    System.out.println(items.size());
    System.out.println(items);

    items = client.listItems(vaults.get(0).getId(), "title co \"Hello\"").join();

    System.out.println(items.size());
    System.out.println(items);

    Item foundItem = client.getItem(vault.getId(), items.get(0).getId()).join();
    System.out.println(foundItem);

    Item createdItem = Item.builder().withTitle("Test Hello World").withCategory(Category.LOGIN).withVault(vault).withFields(Collections.singletonList(new Field(null, Purpose.USERNAME, null, null, "myname", false, null, null, null))).build();
    System.out.println("Creating: " + createdItem);

    Item created = client.createItem(vault.getId(), createdItem).join();
    System.out.println(created);
    Thread.sleep(1000L);
    System.out.println("Deleting");
    client.deleteItem(vault.getId(), created.getId()).join();
    System.out.println("Done deleting");

    System.exit(0);
  }
}

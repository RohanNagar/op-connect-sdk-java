package com.sanctionco.connect;

import com.sanctionco.connect.model.Item;
import com.sanctionco.connect.model.Vault;

import java.util.List;

public class Test {
  public static void main(String[] args) {
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

    Item createdItem = Item.builder().withTitle("Test Hello World").build();
    System.out.println(createdItem);

    System.exit(0);
  }
}

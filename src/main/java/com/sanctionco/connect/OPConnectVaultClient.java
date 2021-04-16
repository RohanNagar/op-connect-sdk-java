package com.sanctionco.connect;

import com.sanctionco.connect.model.Item;
import com.sanctionco.connect.model.Vault;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OPConnectVaultClient {
  private final OPConnectClient client;
  private final String vaultUUID;

  public OPConnectVaultClient(OPConnectClient client, String vaultUUID) {
    this.client = client;
    this.vaultUUID = vaultUUID;
  }

  public CompletableFuture<Vault> getVault() {
    return client.getVault(vaultUUID);
  }

  public CompletableFuture<List<Item>> listItems() {
    return client.listItems(vaultUUID);
  }

  public CompletableFuture<List<Item>> listItems(String filter) {
    return client.listItems(vaultUUID, filter);
  }

  public CompletableFuture<Item> getItem(String itemUUID) {
    return client.getItem(vaultUUID, itemUUID);
  }

  public CompletableFuture<Item> createItem(Item item) {
    return client.createItem(vaultUUID, item);
  }

  public CompletableFuture<Void> deleteItem(String itemUUID) {
    return client.deleteItem(vaultUUID, itemUUID);
  }
}

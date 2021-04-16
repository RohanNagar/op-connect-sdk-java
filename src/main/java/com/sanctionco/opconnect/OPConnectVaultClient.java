package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;
import com.sanctionco.opconnect.model.Vault;

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

  public CompletableFuture<Item> replaceItem(String itemUUID, Item item) {
    return client.replaceItem(vaultUUID, itemUUID, item);
  }

  public CompletableFuture<Item> patchItem(String itemUUID, List<Patch> patches) {
    return client.patchItem(vaultUUID, itemUUID, patches);
  }

  public CompletableFuture<Void> deleteItem(String itemUUID) {
    return client.deleteItem(vaultUUID, itemUUID);
  }
}

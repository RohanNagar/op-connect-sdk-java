package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Filter;
import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;
import com.sanctionco.opconnect.model.Vault;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The {@code OPConnectVaultClient} is a convenient wrapper that provides access to
 * 1Password Connect API methods that interact with a specific vault.
 */
public class OPConnectVaultClient {
  private final OPConnectClient client;
  private final String vaultUUID;

  /**
   * Creates a new instance of {@code OPConnectVaultClient}.
   *
   * @param client the underlying {@link OPConnectClient} to use for making requests
   * @param vaultUUID the id of the vault this client uses when making requests
   */
  public OPConnectVaultClient(OPConnectClient client, String vaultUUID) {
    this.client = client;
    this.vaultUUID = vaultUUID;
  }

  /**
   * Get the details of this vault.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the vault object
   */
  public CompletableFuture<Vault> getVault() {
    return client.getVault(vaultUUID);
  }

  /**
   * List the items from the vault.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of items that exist in the vault, without sections or fields
   */
  public CompletableFuture<List<Item>> listItems() {
    return client.listItems(vaultUUID);
  }

  /**
   * List the items from the vault, filtering based on the filter.
   *
   * @param filter an SCM-style filter to filter the results server-side
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with a
   *         list of items that exist in the vault and match the filter, without sections or fields
   */
  public CompletableFuture<List<Item>> listItems(String filter) {
    return client.listItems(vaultUUID, filter);
  }

  /**
   * List the items from the given vault, filtering based on the filter.
   *
   * @param filter the {@link Filter} to filter the results server-side
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with a
   *         list of items that exist in the vault and match the filter, without sections or fields
   */
  public CompletableFuture<List<Item>> listItems(Filter filter) {
    return client.listItems(vaultUUID, filter.getFilter());
  }

  /**
   * Get a full item from the vault.
   *
   * @param itemUUID the id of the item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the item
   */
  public CompletableFuture<Item> getItem(String itemUUID) {
    return client.getItem(vaultUUID, itemUUID);
  }

  /**
   * Create a new item in the vault.
   *
   * @param item the full item object to create
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the newly created item
   */
  public CompletableFuture<Item> createItem(Item item) {
    return client.createItem(vaultUUID, item);
  }

  /**
   * Replace an entire item in the vault.
   *
   * @param itemUUID the id of the item to replace
   * @param item the full item object that will replace the old item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the newly replaced item
   */
  public CompletableFuture<Item> replaceItem(String itemUUID, Item item) {
    return client.replaceItem(vaultUUID, itemUUID, item);
  }

  /**
   * Applies a list of add, remove, or replace operations on an item or the fields of an item.
   * Uses the <a href="https://tools.ietf.org/html/rfc6902">RFC6902 JSON Patch</a>
   * document standard.
   *
   * @param itemUUID the id of the item to patch
   * @param patches a list of patches to apply to the item in order
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the updated item
   */
  public CompletableFuture<Item> patchItem(String itemUUID, List<Patch> patches) {
    return client.patchItem(vaultUUID, itemUUID, patches);
  }

  /**
   * Applies an add, remove, or replace operation on an item or the fields of an item.
   * Uses the <a href="https://tools.ietf.org/html/rfc6902">RFC6902 JSON Patch</a>
   * document standard.
   *
   * @param itemUUID the id of the item to patch
   * @param patch a patch to apply to the item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the updated item
   */
  public CompletableFuture<Item> patchItem(String itemUUID, Patch patch) {
    return client.patchItem(vaultUUID, itemUUID, patch);
  }

  /**
   * Moves an item to the trash in the vault.
   *
   * @param itemUUID the id of the item to move to the trash
   * @return a {@link CompletableFuture} is returned immediately and eventually completed when the
   *         operation is complete
   */
  public CompletableFuture<Void> deleteItem(String itemUUID) {
    return client.deleteItem(vaultUUID, itemUUID);
  }
}

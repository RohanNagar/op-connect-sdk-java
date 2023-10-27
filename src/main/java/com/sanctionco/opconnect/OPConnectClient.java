package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.File;
import com.sanctionco.opconnect.model.Filter;
import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;
import com.sanctionco.opconnect.model.Vault;
import com.sanctionco.opconnect.model.apiactivity.APIRequest;
import com.sanctionco.opconnect.model.health.ConnectServer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import okhttp3.OkHttpClient;

/**
 * The {@code OPConnectClient} provides access to the 1Password Connect API methods.
 */
public class OPConnectClient {
  private final RetrofitOPConnectClient client;
  private final OkHttpClient httpClient;

  OPConnectClient(RetrofitOPConnectClient client, OkHttpClient httpClient) {
    this.client = client;
    this.httpClient = httpClient;
  }

  /**
   * List the available vaults in 1Password.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the list of available vault objects
   */
  public CompletableFuture<List<Vault>> listVaults() {
    return client.listVaults();
  }

  /**
   * List the available vaults in 1Password, filtering based on the filter.
   *
   * @param filter an SCM-style filter to filter the results server-side
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the list of available vault objects
   */
  public CompletableFuture<List<Vault>> listVaults(String filter) {
    return client.listVaults(filter);
  }

  /**
   * List the available vaults in 1Password, filtering based on the filter.
   *
   * @param filter the {@link Filter} to filter the results server-side
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the list of available vault objects
   */
  public CompletableFuture<List<Vault>> listVaults(Filter filter) {
    return listVaults(filter.getFilter());
  }

  /**
   * Get the details of a specific vault.
   *
   * @param vaultUUID the id of the vault to retrieve
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the vault object
   */
  public CompletableFuture<Vault> getVault(String vaultUUID) {
    return client.getVault(vaultUUID);
  }

  /**
   * List the items from the given vault.
   *
   * @param vaultUUID the id of the vault
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of items that exist in the vault, without sections or fields
   */
  public CompletableFuture<List<Item>> listItems(String vaultUUID) {
    return client.listItems(vaultUUID);
  }

  /**
   * List the items from the given vault, filtering based on the filter.
   *
   * @param vaultUUID the id of the vault
   * @param filter an SCM-style filter to filter the results server-side
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with a
   *         list of items that exist in the vault and match the filter, without sections or fields
   */
  public CompletableFuture<List<Item>> listItems(String vaultUUID, String filter) {
    return client.listItems(vaultUUID, filter);
  }


  /**
   * List the items from the given vault, filtering based on the filter.
   *
   * @param vaultUUID the id of the vault
   * @param filter the {@link Filter} to filter the results server-side
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with a
   *         list of items that exist in the vault and match the filter, without sections or fields
   */
  public CompletableFuture<List<Item>> listItems(String vaultUUID, Filter filter) {
    return listItems(vaultUUID, filter.getFilter());
  }

  /**
   * Get a full item from the given vault.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the item
   */
  public CompletableFuture<Item> getItem(String vaultUUID, String itemUUID) {
    return client.getItem(vaultUUID, itemUUID);
  }

  /**
   * Create a new item in the given vault.
   *
   * @param vaultUUID the id of the vault
   * @param item the full item object to create
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the newly created item
   */
  public CompletableFuture<Item> createItem(String vaultUUID, Item item) {
    return client.createItem(vaultUUID, item);
  }

  /**
   * Replace an entire item in the given vault.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to replace
   * @param item the full item object that will replace the old item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the newly replaced item
   */
  public CompletableFuture<Item> replaceItem(String vaultUUID, String itemUUID, Item item) {
    return client.replaceItem(vaultUUID, itemUUID, item);
  }

  /**
   * Applies a list of add, remove, or replace operations on an item or the fields of an item.
   * Uses the <a href="https://tools.ietf.org/html/rfc6902">RFC6902 JSON Patch</a>
   * document standard.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to patch
   * @param patches a list of patches to apply to the item in order
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the updated item
   */
  public CompletableFuture<Item> patchItem(String vaultUUID, String itemUUID, List<Patch> patches) {
    return client.patchItem(vaultUUID, itemUUID, patches);
  }

  /**
   * Applies one or more of add, remove, or replace operation on an item or the fields of an item.
   * Uses the <a href="https://tools.ietf.org/html/rfc6902">RFC6902 JSON Patch</a>
   * document standard.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to patch
   * @param patches one or more patches to apply to the item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the updated item
   */
  public CompletableFuture<Item> patchItem(String vaultUUID, String itemUUID, Patch... patches) {
    return patchItem(vaultUUID, itemUUID, Arrays.asList(patches));
  }

  /**
   * Moves an item to the trash in the given vault.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to move to the trash
   * @return a {@link CompletableFuture} is returned immediately and eventually completed when the
   *         operation is complete
   */
  public CompletableFuture<Void> deleteItem(String vaultUUID, String itemUUID) {
    return client.deleteItem(vaultUUID, itemUUID);
  }


  /**
   * List the files attached to the given item.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to get files for
   * @param inlineContent whether to include the base64 encoded file contents. The file size must
   *                      be less than OP_MAX_INLINE_FILE_SIZE_KB, or 100 kilobytes if the file
   *                      size isn't defined.
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with the
   *         list of {@link File} objects
   */
  public CompletableFuture<List<File>> listFiles(String vaultUUID,
                                                 String itemUUID,
                                                 boolean inlineContent) {
    return client.listFiles(vaultUUID, itemUUID, inlineContent);
  }

  /**
   * List the files attached to the given item.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to get files for
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with the
   *         list of {@link File} objects
   */
  public CompletableFuture<List<File>> listFiles(String vaultUUID, String itemUUID) {
    return client.listFiles(vaultUUID, itemUUID);
  }

  /**
   * Get the details of a file from the given item.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item that the file is attached to
   * @param fileUUID the id of the file
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with the
   *         {@link File} details
   */
  public CompletableFuture<File> getFile(String vaultUUID, String itemUUID, String fileUUID) {
    return client.getFile(vaultUUID, itemUUID, fileUUID);
  }

  /**
   * Get the details of a file from the given item.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item that the file is attached to
   * @param fileUUID the id of the file
   * @param inlineContent whether to include the base64 encoded file contents. The file size must
   *                      be less than OP_MAX_INLINE_FILE_SIZE_KB, or 100 kilobytes if the file
   *                      size isn't defined.
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with the
   *         {@link File} details
   */
  public CompletableFuture<File> getFile(String vaultUUID,
                                         String itemUUID,
                                         String fileUUID,
                                         boolean inlineContent) {
    return client.getFile(vaultUUID, itemUUID, fileUUID, inlineContent);
  }

  /**
   * Get the content of a file.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item that the file is attached to
   * @param fileUUID the id of the file
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with the
   *         file contents
   */
  public CompletableFuture<String> getFileContent(String vaultUUID,
                                                  String itemUUID,
                                                  String fileUUID) {
    return client.getFileContent(vaultUUID, itemUUID, fileUUID);
  }

  /**
   * Provides a list of recent API activity.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of {@link APIRequest} objects that describe activity
   */
  public CompletableFuture<List<APIRequest>> listAPIActivity() {
    return client.listAPIActivity();
  }

  /**
   * Provides a list of recent API activity, limiting the results based on the given limit.
   *
   * @param limit the maximum number of activity instances to retrieve
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of {@link APIRequest} objects that describe activity
   */
  public CompletableFuture<List<APIRequest>> listAPIActivity(Integer limit) {
    return client.listAPIActivity(limit);
  }

  /**
   * Provides a list of recent API activity, starting at the given offset and limiting the results
   * based on the given limit.
   *
   * @param limit the maximum number of activity instances to retrieve
   * @param offset how far into the collection of API events the response should start
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of {@link APIRequest} objects that describe activity
   */
  public CompletableFuture<List<APIRequest>> listAPIActivity(Integer limit, Integer offset) {
    return client.listAPIActivity(limit, offset);
  }

  /**
   * Retrieves the health of the 1Password connect server.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a {@link ConnectServer} object that describes the server and its health
   */
  public CompletableFuture<ConnectServer> health() {
    return client.health();
  }

  /**
   * Checks the heartbeat of the 1Password connect server, completing exceptionally
   * if the heartbeat fails.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed
   *         successfully if the server has a healthy heartbeat, or completed exceptionally
   *         otherwise
   */
  public CompletableFuture<Void> heartbeat() {
    return client.heartbeat();
  }

  /**
   * Retrieves Prometheus metrics collected by the server.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a plaintext list of Prometheus metrics. See the
   *         <a href="https://prometheus.io/docs/instrumenting/exposition_formats/
   *         #text-based-format">Prometheus documentation</a> for specifics.
   */
  public CompletableFuture<String> metrics() {
    return client.metrics();
  }

  /**
   * Provides a convenient wrapper client that interacts with a specific vault.
   *
   * @param vaultUUID the id of the vault to interact with
   * @return a new {@link OPConnectVaultClient} instance
   */
  public OPConnectVaultClient getVaultClient(String vaultUUID) {
    return new OPConnectVaultClient(this, vaultUUID);
  }

  /**
   * Provides a convenient wrapper client that interacts with a specific vault.
   *
   * @param vault the vault to interact with
   * @return a new {@link OPConnectVaultClient} instance
   */
  public OPConnectVaultClient getVaultClient(Vault vault) {
    return getVaultClient(vault.getId());
  }

  /**
   * Cleanly close the client and any open connections.
   */
  public void close() {
    httpClient.dispatcher().executorService().shutdown();
    httpClient.connectionPool().evictAll();
  }

  /**
   * Creates a new {@link OPConnectClientBuilder} instance to build a client.
   *
   * @return the new builder instance
   */
  public static OPConnectClientBuilder builder() {
    return OPConnectClientBuilder.builder();
  }
}

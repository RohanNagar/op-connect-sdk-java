package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;
import com.sanctionco.opconnect.model.Vault;
import com.sanctionco.opconnect.model.apiactivity.APIRequest;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The {@code OPConnectClient} provides access to the 1Password Connect API methods.
 */
public interface OPConnectClient {

  /**
   * List the available vaults in 1Password.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the list of available vault objects
   */
  @GET("v1/vaults")
  CompletableFuture<List<Vault>> listVaults();

  /**
   * Get the details of a specific vault.
   *
   * @param vaultUUID the id of the vault to retrieve
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the vault object
   */
  @GET("v1/vaults/{id}")
  CompletableFuture<Vault> getVault(@Path("id") String vaultUUID);

  /**
   * List the items from the given vault.
   *
   * @param vaultUUID the id of the vault
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of items that exist in the vault, without sections or fields
   */
  @GET("v1/vaults/{id}/items")
  CompletableFuture<List<Item>> listItems(@Path("id") String vaultUUID);

  /**
   * List the items from the given vault, filtering based on the filter.
   *
   * @param vaultUUID the id of the vault
   * @param filter an SCM-style filter to filter the results server-side
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with a
   *         list of items that exist in the vault and match the filter, without sections or fields
   */
  @GET("v1/vaults/{id}/items")
  CompletableFuture<List<Item>> listItems(@Path("id") String vaultUUID,
                                          @Query("filter") String filter);

  /**
   * Get a full item from the given vault.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the item
   */
  @GET("v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Item> getItem(@Path("vaultId") String vaultUUID,
                                  @Path("itemId") String itemUUID);

  /**
   * Create a new item in the given vault.
   *
   * @param vaultUUID the id of the vault
   * @param item the full item object to create
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the newly created item
   */
  @POST("v1/vaults/{vaultId}/items")
  CompletableFuture<Item> createItem(@Path("vaultId") String vaultUUID,
                                     @Body Item item);

  /**
   * Replace an entire item in the given vault.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to replace
   * @param item the full item object that will replace the old item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the newly replaced item
   */
  @PUT("v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Item> replaceItem(@Path("vaultId") String vaultUUID,
                                      @Path("itemId") String itemUUID,
                                      @Body Item item);

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
  @PATCH("v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Item> patchItem(@Path("vaultId") String vaultUUID,
                                    @Path("itemId") String itemUUID,
                                    @Body List<Patch> patches);

  /**
   * Applies an add, remove, or replace operation on an item or the fields of an item.
   * Uses the <a href="https://tools.ietf.org/html/rfc6902">RFC6902 JSON Patch</a>
   * document standard.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to patch
   * @param patch a patch to apply to the item
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         the updated item
   */
  default CompletableFuture<Item> patchItem(String vaultUUID, String itemUUID, Patch patch) {
    return patchItem(vaultUUID, itemUUID, Collections.singletonList(patch));
  }

  /**
   * Moves an item to the trash in the given vault.
   *
   * @param vaultUUID the id of the vault
   * @param itemUUID the id of the item to move to the trash
   * @return a {@link CompletableFuture} is returned immediately and eventually completed when the
   *         operation is complete
   */
  @DELETE("v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Void> deleteItem(@Path("vaultId") String vaultUUID,
                                     @Path("itemId") String itemUUID);

  /**
   * Provides a list of recent API activity.
   *
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of {@link APIRequest} objects that describe activity
   */
  @GET("v1/activity")
  CompletableFuture<List<APIRequest>> listAPIActivity();

  /**
   * Provides a list of recent API activity, limiting the results based on the given limit.
   *
   * @param limit the maximum number of activity instances to retrieve
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of {@link APIRequest} objects that describe activity
   */
  @GET("v1/activity")
  CompletableFuture<List<APIRequest>> listAPIActivity(@Query("limit") Integer limit);

  /**
   * Provides a list of recent API activity, starting at the given offset and limiting the results
   * based on the given limit.
   *
   * @param limit the maximum number of activity instances to retrieve
   * @param offset how far into the collection of API events the response should start
   * @return a {@link CompletableFuture} is returned immediately and eventually completed with
   *         a list of {@link APIRequest} objects that describe activity
   */
  @GET("v1/activity")
  CompletableFuture<List<APIRequest>> listAPIActivity(@Query("limit") Integer limit,
                                                      @Query("offset") Integer offset);

  /**
   * Provides a convenient wrapper client that interacts with a specific vault.
   *
   * @param vaultUUID the id of the vault to interact with
   * @return a new {@link OPConnectVaultClient} instance
   */
  default OPConnectVaultClient getVaultClient(String vaultUUID) {
    return new OPConnectVaultClient(this, vaultUUID);
  }

  /**
   * Provides a convenient wrapper client that interacts with a specific vault.
   *
   * @param vault the vault to interact with
   * @return a new {@link OPConnectVaultClient} instance
   */
  default OPConnectVaultClient getVaultClient(Vault vault) {
    return getVaultClient(vault.getId());
  }

  /**
   * Creates a new {@link OPConnectClientBuilder} instance to build a client.
   *
   * @return the new builder instance
   */
  static OPConnectClientBuilder builder() {
    return OPConnectClientBuilder.builder();
  }
}

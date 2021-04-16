package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.APIRequest;
import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;
import com.sanctionco.opconnect.model.Vault;

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

public interface OPConnectClient {

  @GET("/v1/vaults")
  CompletableFuture<List<Vault>> listVaults();

  @GET("/v1/vaults/{id}")
  CompletableFuture<Vault> getVault(@Path("id") String vaultUUID);

  @GET("/v1/vaults/{id}/items")
  CompletableFuture<List<Item>> listItems(@Path("id") String vaultUUID);

  @GET("/v1/vaults/{id}/items")
  CompletableFuture<List<Item>> listItems(@Path("id") String vaultUUID,
                                          @Query("filter") String filter);

  @GET("/v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Item> getItem(@Path("vaultId") String vaultUUID,
                                  @Path("itemId") String itemUUID);

  @POST("/v1/vaults/{vaultId}/items")
  CompletableFuture<Item> createItem(@Path("vaultId") String vaultUUID,
                                     @Body Item item);

  @PUT("/v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Item> replaceItem(@Path("vaultId") String vaultUUID,
                                      @Path("itemId") String itemUUID,
                                      @Body Item item);

  @PATCH("/v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Item> patchItem(@Path("vaultId") String vaultUUID,
                                    @Path("itemId") String itemUUID,
                                    @Body List<Patch> patches);

  @DELETE("/v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Void> deleteItem(@Path("vaultId") String vaultUUID,
                                     @Path("itemId") String itemUUID);

  @GET("/v1/activity")
  CompletableFuture<List<APIRequest>> listAPIActivity();

  @GET("/v1/activity")
  CompletableFuture<List<APIRequest>> listAPIActivity(@Query("limit") Integer limit);

  @GET("/v1/activity")
  CompletableFuture<List<APIRequest>> listAPIActivity(@Query("limit") Integer limit,
                                                      @Query("offset") Integer offset);

  default OPConnectVaultClient getVaultClient(String vaultUUID) {
    return new OPConnectVaultClient(this, vaultUUID);
  }
}

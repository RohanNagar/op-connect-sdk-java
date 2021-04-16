package com.sanctionco.connect;

import com.sanctionco.connect.model.Item;
import com.sanctionco.connect.model.Vault;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

  @DELETE("/v1/vaults/{vaultId}/items/{itemId}")
  CompletableFuture<Void> deleteItem(@Path("vaultId") String vaultUUID,
                                     @Path("itemId") String itemUUID);

  default OPConnectVaultClient getVaultClient(String vaultUUID) {
    return new OPConnectVaultClient(this, vaultUUID);
  }
}

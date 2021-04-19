package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Vault;

import io.dropwizard.testing.junit5.DropwizardClientExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import retrofit2.HttpException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(DropwizardExtensionsSupport.class)
class OPConnectClientTest {

  /**
   * Resource to be used as a test double. Requests from the OPConnectClient interface
   * will be directed here for unit tests.
   */
  @Path("/v1")
  @Produces(MediaType.APPLICATION_JSON)
  public static final class TestResource {

    @GET
    @Path("/vaults")
    public Response mockListVaults() {
      return Response.ok()
          .entity(Collections.singletonList(Vault.builder().withId("test").build()))
          .build();
    }

    @GET
    @Path("/vaults/{id}")
    public Response mockGetVault(@PathParam("id") String id) {
      if (id.equals("notfound")) {
        return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
      }

      return Response.ok().entity(Vault.builder().withId(id).build()).build();
    }
  }

  private static final DropwizardClientExtension extension =
      new DropwizardClientExtension(new TestResource());

  private final OPConnectClient client = OPConnectClient.builder()
      .withEndpoint(extension.baseUri().toString())
      .withAccessToken("testToken")
      .build();

  @Test
  void testListVaults() {
    List<Vault> response = client.listVaults().join();

    assertEquals(1, response.size());
    assertEquals("test", response.get(0).getId());
  }

  @Test
  void testGetVault() {
    Vault response = client.getVault("hello-world").join();

    assertEquals("hello-world", response.getId());
  }

  @Test
  void testGetVaultNotFound() {
    Vault response = client.getVault("notfound").exceptionally(throwable -> {
      assertNotNull(throwable);
      assertTrue(throwable instanceof HttpException);

      assertEquals(404, ((HttpException) throwable).code());

      return Vault.builder().withId("ownvault").build();
    }).join();

    assertEquals("ownvault", response.getId());
  }
}

package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OPConnectVaultClientTest {

  @Test
  void vaultClientPassesCallsThrough() {
    final OPConnectClient client = mock(OPConnectClient.class);
    final Item item = Item.builder().build();
    final Patch patch = Patch.builder().build();

    OPConnectVaultClient vaultClient = new OPConnectVaultClient(client, "testId");

    vaultClient.getVault();
    verify(client, times(1)).getVault(eq("testId"));

    vaultClient.listItems();
    verify(client, times(1)).listItems(eq("testId"));

    vaultClient.listItems("filter");
    verify(client, times(1)).listItems(eq("testId"), eq("filter"));

    vaultClient.getItem("testItemId");
    verify(client, times(1)).getItem(eq("testId"), eq("testItemId"));

    vaultClient.createItem(item);
    verify(client, times(1))
        .createItem(eq("testId"), same(item));

    vaultClient.replaceItem("itemId", item);
    verify(client, times(1))
        .replaceItem(eq("testId"), eq("itemId"), same(item));

    vaultClient.patchItem("itemId", Collections.singletonList(patch));
    verify(client, times(1))
        .patchItem(eq("testId"), eq("itemId"), eq(Collections.singletonList(patch)));

    vaultClient.patchItem("itemId", patch);
    verify(client, times(1))
        .patchItem(eq("testId"), eq("itemId"), same(patch));

    vaultClient.deleteItem("itemId");
    verify(client, times(1)).deleteItem(eq("testId"), eq("itemId"));
  }
}
